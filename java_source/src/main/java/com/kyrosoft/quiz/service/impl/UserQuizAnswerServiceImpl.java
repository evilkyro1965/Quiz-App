package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.UserQuizAnswer;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.entity.dto.UserQuizAnswerSearchCriteria;
import com.kyrosoft.quiz.service.UserQuizAnswerService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * Created by Administrator on 2/20/2016.
 */
@Repository
@Transactional
public class UserQuizAnswerServiceImpl extends BaseServiceImpl<UserQuizAnswer,UserQuizAnswerSearchCriteria>
    implements UserQuizAnswerService {

    public UserQuizAnswerServiceImpl() {
        super(UserQuizAnswer.class);
    }


    @Override
    protected String generateWhereClause(UserQuizAnswerSearchCriteria criteria) throws ServiceException {
        StringBuffer sb = new StringBuffer("WHERE (1=1)");

        if (criteria.getUserId() != null) {
            sb.append(" AND e.userOwnedId = :userOwnedId");
        }

        if (criteria.getQuizId() != null) {
            sb.append(" AND e.quiz.id = :quizId");
        }

        return sb.toString();
    }

    @Override
    protected void populateQueryParameters(Query query, UserQuizAnswerSearchCriteria criteria) throws ServiceException {
        if (criteria.getUserId() != null) {
            query.setParameter("userOwnedId", criteria.getUserId());
        }

        if (criteria.getQuizId() != null) {
            query.setParameter("quizId", criteria.getQuizId());
        }
    }

    public UserQuizAnswer get(long id) {
        UserQuizAnswer userQuizAnswer = entityManager.find(UserQuizAnswer.class,id);
        return userQuizAnswer;
    }

    public UserQuizAnswer save(UserQuizAnswer userQuizAnswer) throws ServiceException, DatabasePersistenceException {
        entityManager.persist(userQuizAnswer);
        return userQuizAnswer;
    }

    public UserQuizAnswer update(UserQuizAnswer userQuizAnswer) throws ServiceException, DatabasePersistenceException {
        entityManager.merge(userQuizAnswer);
        return userQuizAnswer;
    }

    public void delete(Long id) throws ServiceException, DatabasePersistenceException {
        UserQuizAnswer userQuizAnswer = entityManager.find(UserQuizAnswer.class,id);
        entityManager.remove(userQuizAnswer);
    }
}
