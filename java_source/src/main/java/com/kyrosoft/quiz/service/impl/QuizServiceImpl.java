package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.QuizCategory;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import com.kyrosoft.quiz.service.QuizService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * Created by Administrator on 2/18/2016.
 */
@Repository
@Transactional
public class QuizServiceImpl extends BaseServiceImpl<Quiz,QuizSearchCriteria>
    implements QuizService {

    public QuizServiceImpl() {
        super(Quiz.class);
    }

    @Override
    protected String generateWhereClause(QuizSearchCriteria criteria) throws ServiceException {
        StringBuffer sb = new StringBuffer("WHERE (1=1)");

        if (criteria.getUserId() != null) {
            sb.append(" AND e.userOwnedId = :userOwnedId");
        }

        if (criteria.getCategoryId() != null) {
            sb.append(" AND e.category.id = :categoryId");
        }

        return sb.toString();
    }

    @Override
    protected void populateQueryParameters(Query query, QuizSearchCriteria criteria) throws ServiceException {
        if (criteria.getUserId() != null) {
            query.setParameter("userOwnedId", criteria.getUserId());
        }

        if (criteria.getCategoryId() != null) {
            query.setParameter("categoryId", criteria.getCategoryId());
        }
    }

    public Quiz get(long id) {
        Quiz quiz = entityManager.find(Quiz.class,id);
        return quiz;
    }

    public Quiz save(Quiz quiz) throws ServiceException, DatabasePersistenceException {

        if(quiz.getCategory()!=null) {
            QuizCategory quizCategory = entityManager.find(QuizCategory.class,quiz.getCategory().getId());
            quiz.setCategory(quizCategory);
        }

        entityManager.persist(quiz);
        return quiz;
    }

    public Quiz update(Quiz quiz) throws ServiceException, DatabasePersistenceException {

        if(quiz.getCategory()!=null) {
            QuizCategory quizCategory = entityManager.find(QuizCategory.class,quiz.getCategory().getId());
            quiz.setCategory(quizCategory);
        }

        entityManager.merge(quiz);

        return quiz;
    }

    public void delete(Long id) throws ServiceException, DatabasePersistenceException {

    }
}
