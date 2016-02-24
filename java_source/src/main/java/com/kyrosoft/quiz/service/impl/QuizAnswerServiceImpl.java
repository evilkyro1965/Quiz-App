package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.MultipleChoiceQuizAnswer;
import com.kyrosoft.quiz.entity.dto.UserQuizAnswerSearchCriteria;
import com.kyrosoft.quiz.service.QuizAnswerService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * Created by Administrator on 2/20/2016.
 */
@Repository
@Transactional
public class QuizAnswerServiceImpl extends BaseServiceImpl<MultipleChoiceQuizAnswer,UserQuizAnswerSearchCriteria>
    implements QuizAnswerService {

    public QuizAnswerServiceImpl() {
        super(MultipleChoiceQuizAnswer.class);
    }

    @Override
    protected String generateWhereClause(UserQuizAnswerSearchCriteria criteria) throws ServiceException {
        return null;
    }

    @Override
    protected void populateQueryParameters(Query query, UserQuizAnswerSearchCriteria criteria) throws ServiceException {

    }

    public MultipleChoiceQuizAnswer get(long id) {
        MultipleChoiceQuizAnswer multipleChoiceQuizAnswer = entityManager.find(MultipleChoiceQuizAnswer.class,id);
        return multipleChoiceQuizAnswer;
    }

    public MultipleChoiceQuizAnswer save(MultipleChoiceQuizAnswer quizAnswer) throws ServiceException, DatabasePersistenceException {
        entityManager.persist(quizAnswer);
        return quizAnswer;
    }

    public MultipleChoiceQuizAnswer update(MultipleChoiceQuizAnswer quizAnswer) throws ServiceException, DatabasePersistenceException {
        entityManager.merge(quizAnswer);
        return quizAnswer;
    }
}
