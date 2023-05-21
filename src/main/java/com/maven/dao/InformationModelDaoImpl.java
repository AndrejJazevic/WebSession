package com.maven.dao;

import com.maven.entity.InformationModel;
import com.maven.entity.InformationModel_;
import com.maven.entity.Session;
import com.maven.entity.User_;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class InformationModelDaoImpl implements InformationModelDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public InformationModel updateModel(Session session, String text) {
        InformationModel informationModel = getOrCreate(session);
        informationModel.setText(text);
        em.persist(informationModel);
        return informationModel;
    }

    @Override
    public InformationModel getOrCreate(Session session){

        Integer userId = session.getUser().getId();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<InformationModel> query = cb.createQuery(InformationModel.class);

        Root<InformationModel> root = query.from(InformationModel.class);

        Predicate predicate = cb.equal(root.get(InformationModel_.user).get(User_.id), userId);

        query.where(predicate);
        query.select(root);

        List<InformationModel> resultList = em.createQuery(query).getResultList();
        if(resultList.size() >1){
            throw new IllegalStateException("More than one information model, stuff is broken");
        }
        else if(resultList.size()==1){
            return resultList.get(0);
        }

        InformationModel informationModel = new InformationModel();
        informationModel.setUser(session.getUser());
        informationModel.setText("");
        em.persist(informationModel);

        return informationModel;

    }
}
