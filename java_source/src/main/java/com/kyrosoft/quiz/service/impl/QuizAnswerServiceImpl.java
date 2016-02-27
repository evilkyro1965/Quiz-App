package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.MultipleChoiceQuizAnswer;
import com.kyrosoft.quiz.entity.dto.UserSessionSearchCriteria;
import com.kyrosoft.quiz.service.QuizAnswerService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2/20/2016.
 */
@Repository
@Transactional
public class QuizAnswerServiceImpl extends BaseServiceImpl<MultipleChoiceQuizAnswer,UserSessionSearchCriteria>
    implements QuizAnswerService {

    public QuizAnswerServiceImpl() {
        super(MultipleChoiceQuizAnswer.class);
    }

    @Override
    protected String generateWhereClause(UserSessionSearchCriteria criteria) throws ServiceException {
        return null;
    }

    @Override
    protected void populateQueryParameters(Query query, UserSessionSearchCriteria criteria) throws ServiceException {

    }

    public MultipleChoiceQuizAnswer get(long id) {
        MultipleChoiceQuizAnswer multipleChoiceQuizAnswer = entityManager.find(MultipleChoiceQuizAnswer.class,id);
        return multipleChoiceQuizAnswer;
    }

    public MultipleChoiceQuizAnswer get(Long quizSessionId,Long questionId) {
        String className = ServiceHelper.getClassNameForSearch(MultipleChoiceQuizAnswer.class);
        Query query = entityManager.createQuery(
                "SELECT a FROM "+className+" a WHERE a.quizSession.id = :quizSessionId AND a.question.id = :questionId")
                .setParameter("quizSessionId", quizSessionId)
                .setParameter("questionId", questionId);
        List<MultipleChoiceQuizAnswer> result = query.getResultList();
        if(result!=null && result.size()>0) {
            return result.get(0);
        }
        return null;
    }

    public List<MultipleChoiceQuizAnswer> getAnswerList(Long quizSessionId) {
        List<MultipleChoiceQuizAnswer> answerList = new ArrayList<MultipleChoiceQuizAnswer>();

        String className = ServiceHelper.getClassNameForSearch(MultipleChoiceQuizAnswer.class);
        Query query = entityManager.createQuery(
                "SELECT a FROM "+className+" a WHERE a.quizSession.id = :quizSessionId")
                .setParameter("quizSessionId", quizSessionId);
        answerList = query.getResultList();
        return answerList;
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
