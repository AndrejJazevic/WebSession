//package com.maven.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.maven.*;
//import com.maven.dao.UserDao;
//import com.maven.entity.User;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//@Controller
//public class WebController {
//    private static AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//    UserDao userDao = ctx.getBean(UserDao.class);
//
//    @RequestMapping(value = "/login")
//    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
//        String username=request.getParameter("username");
//        String password=request.getParameter("password");
//        String message;
//        List<User> users = userDao.findAll();
//        for(User user:users){
//            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
//                message = "Welcome " +username + ".";
//                return new ModelAndView("login", "message", message);
//            }
//        }
//        message = "Wrong username or password.";
//        return new ModelAndView("errorPage", "message", message);
//
////        if(userName != null &&
////                !userName.equals("")
////                && userName.equals("jai") &&
////                password != null &&
////                !password.equals("") &&
////                password.equals("123")){
////            message = "Welcome " +userName + ".";
////            return new ModelAndView("text",
////                    "message", message);
////
////        }else{
////            message = "Wrong username or password.";
////            return new ModelAndView("errorPage",
////                    "message", message);
////        }
//    }
//    @RequestMapping(value = "/")
//    public void logout(HttpServletRequest request, HttpServletResponse response) {
//
//    }
//
//    @RequestMapping(value = "/")
//    public void saveInfoModel(HttpServletRequest request, HttpServletResponse response) {
//
//    }
//}