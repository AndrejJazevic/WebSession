package com.maven.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(InformationModel.class)
public abstract class InformationModel_ {

	public static volatile SingularAttribute<InformationModel, Integer> id;
	public static volatile SingularAttribute<InformationModel, String> text;
	public static volatile SingularAttribute<InformationModel, User> user;

	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String USER = "user";

}

