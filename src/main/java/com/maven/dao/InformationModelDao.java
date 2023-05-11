package com.maven.dao;

import com.maven.entity.InformationModel;
import com.maven.entity.Session;

public interface InformationModelDao {
    InformationModel createModel(Session session, String text);
}
