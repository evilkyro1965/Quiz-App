package com.kyrosoft.quiz.service;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.MultipleChoiceQuizAnswer;
import com.kyrosoft.quiz.entity.UserQuizAnswer;

/**
 * Created by Administrator on 2/20/2016.
 */
public interface QuizAnswerService {

    public MultipleChoiceQuizAnswer get(long id);

    public MultipleChoiceQuizAnswer save(MultipleChoiceQuizAnswer quizAnswer) throws ServiceException, DatabasePersistenceException;

    public MultipleChoiceQuizAnswer update(MultipleChoiceQuizAnswer quizAnswer) throws ServiceException, DatabasePersistenceException;

}
