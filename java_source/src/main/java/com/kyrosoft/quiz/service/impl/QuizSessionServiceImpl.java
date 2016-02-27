package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.QuizSession;
import com.kyrosoft.quiz.entity.dto.UserSessionSearchCriteria;
import com.kyrosoft.quiz.service.QuizSessionService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * Created by Administrator on 2/20/2016.
 */
@Repository
@Transactional
public class QuizSessionServiceImpl extends BaseServiceImpl<QuizSession,UserSessionSearchCriteria>
    implements QuizSessionService {

    public QuizSessionServiceImpl() {
        super(QuizSession.class);
    }


    @Override
    protected String generateWhereClause(UserSessionSearchCriteria criteria) throws ServiceException {
        StringBuffer sb = new StringBuffer("WHERE (1=1)");

        if (criteria.getUserId() != null) {
            sb.append(" AND e.userOwnedId = :userOwnedId");
        }

        if (criteria.getQuizId() != null) {
            sb.append(" AND e.quiz.id = :quizId");
        }

        if (criteria.getCompleted() != null) {
            sb.append(" AND e.isCompleted = :isCompleted");
        }

        return sb.toString();
    }

    @Override
    protected void populateQueryParameters(Query query, UserSessionSearchCriteria criteria) throws ServiceException {
        if (criteria.getUserId() != null) {
            query.setParameter("userOwnedId", criteria.getUserId());
        }

        if (criteria.getQuizId() != null) {
            query.setParameter("quizId", criteria.getQuizId());
        }

        if (criteria.getCompleted() != null) {
            query.setParameter("isCompleted", criteria.getCompleted());
        }
    }

    public QuizSession get(long id) {
        QuizSession userQuizAnswer = entityManager.find(QuizSession.class,id);
        return userQuizAnswer;
    }

    public QuizSession save(QuizSession userQuizAnswer) throws ServiceException, DatabasePersistenceException {
        entityManager.persist(userQuizAnswer);
        return userQuizAnswer;
    }

    public QuizSession update(QuizSession userQuizAnswer) throws ServiceException, DatabasePersistenceException {
        entityManager.merge(userQuizAnswer);
        return userQuizAnswer;
    }

    public void delete(Long id) throws ServiceException, DatabasePersistenceException {
        QuizSession userQuizAnswer = entityManager.find(QuizSession.class,id);
        entityManager.remove(userQuizAnswer);
    }
}
