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
        createQuizSession();
        createQuizQuestion();

        MultipleChoiceQuizAnswer quizAnswer = new MultipleChoiceQuizAnswer();
        quizAnswer.setAnswer(MultipleChoiceAnswer.A);
        quizAnswer.setQuestion(this.quizQuestion);
        quizAnswer.setQuizSession(this.quizSession);
        quizAnswer.setUserOwnedId(IdTest);

        quizAnswerService.save(quizAnswer);
    }

    @Test
    public void updateQuizAnswerTest() throws ServiceException, DatabasePersistenceException {
        createQuizSession();

        MultipleChoiceQuizAnswer quizAnswer = new MultipleChoiceQuizAnswer();
        quizAnswer.setAnswer(MultipleChoiceAnswer.A);
        quizAnswer.setQuestion(this.quizQuestion);
        quizAnswer.setQuizSession(this.quizSession);
        quizAnswer.setUserOwnedId(IdTest);

        quizAnswerService.save(quizAnswer);
        quizAnswer.setAnswer(MultipleChoiceAnswer.B);

        quizAnswerService.update(quizAnswer);
    }
}
