package com.maven.dao;

import com.maven.entity.Session;
import com.maven.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Component
@Transactional
public class SessionDaoImpl implements SessionDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Session createLogin(User user) {
        Session session = new Session();
        session.setLoginTime(LocalDate.now());
        session.setUser(user);
        return session;
    }

    @Override
    public void updateLogout(Session session) {
        session.setLogoutTime(LocalDate.now());
        em.persist(session);
    }

}
