package com.kyrosoft.quiz.service;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.MultipleChoiceQuizAnswer;

import java.util.List;

/**
 * Created by Administrator on 2/20/2016.
 */
public interface QuizAnswerService {

    public MultipleChoiceQuizAnswer get(long id);

    public MultipleChoiceQuizAnswer get(Long quizSessionId, Long questionId);

    public List<MultipleChoiceQuizAnswer> getAnswerList(Long quizSessionId);

    public MultipleChoiceQuizAnswer save(MultipleChoiceQuizAnswer quizAnswer) throws ServiceException, DatabasePersistenceException;

    public MultipleChoiceQuizAnswer update(MultipleChoiceQuizAnswer quizAnswer) throws ServiceException, DatabasePersistenceException;

}
