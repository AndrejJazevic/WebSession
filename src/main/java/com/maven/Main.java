package com.maven;

import com.maven.dao.SessionDao;
import com.maven.dao.UserDao;
import com.maven.entity.Session;
import com.maven.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Main {
    private static AnnotationConfigApplicationContext ctx;

    public static void main(String args[]) throws InterruptedException {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.getBean(AppConfig.class);

        String username = "lukas";
        String password = "1111";
        UserDao userDao = ctx.getBean(UserDao.class);
        SessionDao sessionDao = ctx.getBean(SessionDao.class);

        List<User> users = userDao.findAll();
        for(User user:users){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                System.out.println("You logged in!");
//                Session session = sessionDao.createLogin(user);
//                Thread.sleep(2000);
//                sessionDao.updateLogout(session);
                return;
            }
        }
        System.out.println("The username or password is incorrect.");
    }
}
