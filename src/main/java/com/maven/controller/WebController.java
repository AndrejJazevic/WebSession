package com.maven.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maven.dao.InformationModelDao;
import com.maven.dao.SessionDao;
import com.maven.dao.UserDao;
import com.maven.entity.InformationModel;
import com.maven.entity.Session;
import com.maven.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class WebController {
    @Autowired
    UserDao userDao;
    @Autowired
    SessionDao sessionDao;
    @Autowired
    InformationModelDao informationModelDao;

    public ModelAndView createTextInputPage(Session session){
        InformationModel informationModel = informationModelDao.getOrCreate(session);
        String message = "Welcome " + session.getUser().getUsername() + ".";

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("message", message);
        model.put("textValue", informationModel.getText());

        return new ModelAndView("text", "model", model);
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message;
        List<User> users = userDao.findAll();
        if(username != null && password != null && !username.equals("") && !password.equals("")){
            for(User user:users){
                if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                    Session session1 = sessionDao.createSession(user);
                    session.setAttribute("username", user);
                    session.setAttribute("session", session1);
                    return createTextInputPage(session1);
                }
            }
            message = "Wrong username or password.";
            return new ModelAndView("errorPage", "message", message);
        }
        else if(username == null && password == null)
        {
            return new ModelAndView("login");
        }
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session) {
        Session session1 = (Session) session.getAttribute("session");
        if(session1 == null){
            return new ModelAndView("login");
        }
        session.removeAttribute("session");
        session.removeAttribute("username");
        sessionDao.updateLogout(session1);
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/save")
    public ModelAndView saveInfoModel(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Session session1 = (Session) session.getAttribute("session");
        if(session1 == null){
            return new ModelAndView("login");
        }

        String text = Optional.ofNullable(request.getParameter("userText"))
                .map(StringUtils::trim)
                .orElse("");
        informationModelDao.updateModel(session1, text);

        return createTextInputPage(session1);
    }
}