package com.maven.dao;

import com.maven.entity.InformationModel;
import com.maven.entity.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class InformationModelDaoImpl implements InformationModelDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public InformationModel createModel(Session session, String text) {
        InformationModel informationModel = new InformationModel();
        informationModel.setSession(session);
        informationModel.setText(text);
        em.persist(informationModel);
        return informationModel;
    }
}
