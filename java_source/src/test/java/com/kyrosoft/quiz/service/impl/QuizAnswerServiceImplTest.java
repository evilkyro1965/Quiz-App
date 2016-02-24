package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.MultipleChoiceAnswer;
import com.kyrosoft.quiz.entity.MultipleChoiceQuizAnswer;
import org.junit.Test;

/**
 * Created by Administrator on 2/20/2016.
 */
public class QuizAnswerServiceImplTest extends BaseTest {

    @Test
    public void createQuizAnswerTest() throws ServiceException, DatabasePersistenceException {
        createUserQuizAnswer();

        MultipleChoiceQuizAnswer quizAnswer = new MultipleChoiceQuizAnswer();
        quizAnswer.setNo(1);
        quizAnswer.setAnswer(MultipleChoiceAnswer.A);
        quizAnswer.setUserQuizAnswer(this.userQuizAnswer);
        quizAnswer.setUserOwnedId(IdTest);

        quizAnswerService.save(quizAnswer);
    }

    @Test
    public void updateQuizAnswerTest() throws ServiceException, DatabasePersistenceException {
        createUserQuizAnswer();

        MultipleChoiceQuizAnswer quizAnswer = new MultipleChoiceQuizAnswer();
        quizAnswer.setNo(1);
        quizAnswer.setAnswer(MultipleChoiceAnswer.A);
        quizAnswer.setUserQuizAnswer(this.userQuizAnswer);
        quizAnswer.setUserOwnedId(IdTest);

        quizAnswerService.save(quizAnswer);
        quizAnswer.setAnswer(MultipleChoiceAnswer.B);

        quizAnswerService.update(quizAnswer);
    }
}
