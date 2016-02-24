package com.kyrosoft.quiz.service.impl;


import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.IdentifiableEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Administrator on 11/8/15.
 */
@Repository
public class SaveHelperImpl implements SaveHelper {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public <T> T save(T entity) throws ServiceException, DatabasePersistenceException {
        IdentifiableEntity saveEntity = (IdentifiableEntity) entity;
        if(saveEntity.getId()==null || saveEntity.getId()==0 )
            entityManager.persist(entity);
        return entity;
    }
}
