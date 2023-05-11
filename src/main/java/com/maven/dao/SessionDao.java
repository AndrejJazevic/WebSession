package com.maven.dao;

import com.maven.entity.Session;
import com.maven.entity.User;

public interface SessionDao {
    Session createSession(User user);
    void updateLogout(Session session);
}
