package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.QuizQuestion;
import com.kyrosoft.quiz.entity.dto.QuizQuestionSearchCriteria;
import com.kyrosoft.quiz.service.QuizQuestionService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * Created by Administrator on 2/18/2016.
 */
@Repository
@Transactional
public class QuizQuestionServiceImpl extends BaseServiceImpl<QuizQuestion,QuizQuestionSearchCriteria>
    implements QuizQuestionService {

    public QuizQuestionServiceImpl() {
        super(QuizQuestion.class);
    }


    @Override
    protected String generateWhereClause(QuizQuestionSearchCriteria criteria) throws ServiceException {
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
    protected void populateQueryParameters(Query query, QuizQuestionSearchCriteria criteria) throws ServiceException {
        if (criteria.getUserId() != null) {
            query.setParameter("userOwnedId", criteria.getUserId());
        }

        if (criteria.getQuizId() != null) {
            query.setParameter("quizId", criteria.getQuizId());
        }
    }

    public QuizQuestion get(long id) {
        QuizQuestion quizQuestion = entityManager.find(QuizQuestion.class,id);
        return quizQuestion;
    }

    public QuizQuestion save(QuizQuestion quizQuestion) throws ServiceException, DatabasePersistenceException {

        entityManager.persist(quizQuestion);
        return quizQuestion;
    }

    public QuizQuestion update(QuizQuestion quizQuestion) throws ServiceException, DatabasePersistenceException {

        entityManager.merge(quizQuestion);
        return quizQuestion;
    }

    public void delete(Long id) throws ServiceException, DatabasePersistenceException {
        QuizQuestion quizQuestion = entityManager.find(QuizQuestion.class,id);
        entityManager.remove(quizQuestion);
    }
}
