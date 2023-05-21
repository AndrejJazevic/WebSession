package com.maven.entity;

import java.time.LocalDateTime;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Session.class)
public abstract class Session_ {

	public static volatile SingularAttribute<Session, LocalDateTime> logoutTime;
	public static volatile SingularAttribute<Session, LocalDateTime> loginTime;
	public static volatile SingularAttribute<Session, Integer> id;
	public static volatile SingularAttribute<Session, User> user;

	public static final String LOGOUT_TIME = "logoutTime";
	public static final String LOGIN_TIME = "loginTime";
	public static final String ID = "id";
	public static final String USER = "user";

}

