package com.maven.dao;

import com.maven.entity.InformationModel;
import com.maven.entity.Session;

public interface InformationModelDao {
    InformationModel updateModel(Session session, String text);

    InformationModel getOrCreate(Session session);
}
