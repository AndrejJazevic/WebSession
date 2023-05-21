package com.maven.dao;

import com.maven.entity.InformationModel;
import com.maven.entity.Session;
import com.maven.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Transactional
public class SessionDaoImpl implements SessionDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Session createSession(User user) {
        Session session = new Session();
        session.setLoginTime(LocalDateTime.now());
        session.setUser(user);
        em.persist(session);
        return session;
    }

    public boolean needToMerge(Object obj) throws Exception{
        Method getId = obj.getClass().getDeclaredMethod("getId");
        getId.setAccessible(true);
        Object invoke = getId.invoke(obj);
        if(invoke == null){
            return false;
        }
        return true;
    }

    @Override
    public void updateLogout(Session session) {
        session.setLogoutTime(LocalDateTime.now());
        try {
            if(needToMerge(session)){
                em.merge(session);
            }
            else {
                em.persist(session);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
