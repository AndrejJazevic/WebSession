package com.maven.dao;

import com.maven.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
