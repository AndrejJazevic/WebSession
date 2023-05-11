package com.maven.dao;

import com.maven.entity.Session;
import com.maven.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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

    @Override
    public void updateLogout(Session session) {
        session.setLogoutTime(LocalDateTime.now());
        // figure out if session if mergeable or not
        em.merge(session);
    }

}
