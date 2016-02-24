package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.QuizCategory;
import com.kyrosoft.quiz.entity.User;
import com.kyrosoft.quiz.entity.dto.QuizCategorySearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.service.QuizCategoryService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * Created by Administrator on 2/18/2016.
 */
@Repository
@Transactional
public class QuizCategoryServiceImpl extends BaseServiceImpl<QuizCategory,QuizCategorySearchCriteria>
    implements QuizCategoryService {

    /**
     * Constructor.
     *
     * @throws IllegalArgumentException if entityClass is null.
     */
    protected QuizCategoryServiceImpl() {
        super(QuizCategory.class);
    }

    public QuizCategory get(long id) {
        QuizCategory quizCategory = entityManager.find(QuizCategory.class,id);
        return quizCategory;
    }

    public QuizCategory save(QuizCategory quizCategory) throws ServiceException, DatabasePersistenceException {

        entityManager.persist(quizCategory);

        return quizCategory;
    }

    public QuizCategory update(QuizCategory quizCategory) throws ServiceException, DatabasePersistenceException {

        entityManager.merge(quizCategory);

        return quizCategory;
    }

    public void delete(Long id) throws ServiceException, DatabasePersistenceException {
        QuizCategory quizCategory = entityManager.find(QuizCategory.class,id);
        entityManager.remove(quizCategory);
    }

    @Override
    protected String generateWhereClause(QuizCategorySearchCriteria criteria) throws ServiceException {
        StringBuffer sb = new StringBuffer("WHERE (1=1)");

        return sb.toString();
    }

    @Override
    protected void populateQueryParameters(Query query, QuizCategorySearchCriteria criteria) throws ServiceException {

    }
}
