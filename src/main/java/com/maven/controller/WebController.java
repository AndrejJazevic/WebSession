package com.maven.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maven.*;
import com.maven.dao.InformationModelDao;
import com.maven.dao.SessionDao;
import com.maven.dao.UserDao;
import com.maven.entity.InformationModel;
import com.maven.entity.Session;
import com.maven.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    UserDao userDao;
    @Autowired
    SessionDao sessionDao;
    @Autowired
    InformationModelDao informationModelDao;

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
                    message = "Welcome " + username + ".";
                    return new ModelAndView("text", "message", message);
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
        session.removeAttribute("session");
        session.removeAttribute("username");
        sessionDao.updateLogout(session1);
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/save")
    public ModelAndView saveInfoModel(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String text = request.getParameter("userText");
        Session session1 = (Session) session.getAttribute("session");
        informationModelDao.createModel(session1, text);
        return new ModelAndView("text");
    }
}