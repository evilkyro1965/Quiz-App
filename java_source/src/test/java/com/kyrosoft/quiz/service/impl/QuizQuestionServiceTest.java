package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.MultipleChoiceAnswer;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.QuizQuestion;
import com.kyrosoft.quiz.entity.dto.QuizQuestionSearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2/18/2016.
 */
public class QuizQuestionServiceTest extends BaseTest {

    @Test
    public void createQuizQuestionTest() throws ServiceException, DatabasePersistenceException {
        createQuizCategory();
        createQuiz();
        Quiz quiz = new Quiz();
        quiz.setId(IdTest);

        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setChoiceA("1");
        quizQuestion.setChoiceB("2");
        quizQuestion.setChoiceC("3");
        quizQuestion.setChoiceD("4");
        quizQuestion.setCorrectAnswer(MultipleChoiceAnswer.A);
        quizQuestion.setQuestion("Lorem ipsum");
        quizQuestion.setUserOwnedId(IdTest);

        quizQuestion.setNo(1);
        quizQuestion.setUserOwnedId(IdTest);
        quizQuestion.setQuiz(quiz);

        quizQuestionService.save(quizQuestion);
    }

    @Test
    public void updateQuizQuestionTest() throws ServiceException, DatabasePersistenceException {
        createQuizCategory();
        Quiz quiz = createQuiz();

        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setChoiceA("1");
        quizQuestion.setChoiceB("2");
        quizQuestion.setChoiceC("3");
        quizQuestion.setChoiceD("4");
        quizQuestion.setCorrectAnswer(MultipleChoiceAnswer.A);
        quizQuestion.setQuestion("Lorem ipsum");
        quizQuestion.setUserOwnedId(IdTest);

        quizQuestion.setNo(1);
        quizQuestion.setUserOwnedId(IdTest);
        quizQuestion.setQuiz(quiz);

        quizQuestionService.save(quizQuestion);
        quizQuestion.setNo(2);

        quizQuestionService.update(quizQuestion);
    }

    @Test
    public void searchQuizQuestionTest() throws ServiceException, DatabasePersistenceException {
        createQuizCategory();
        createQuiz();
        Quiz quiz = new Quiz();
        quiz.setId(IdTest);

        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setChoiceA("1");
        quizQuestion.setChoiceB("2");
        quizQuestion.setChoiceC("3");
        quizQuestion.setChoiceD("4");
        quizQuestion.setCorrectAnswer(MultipleChoiceAnswer.A);
        quizQuestion.setQuestion("Lorem ipsum");
        quizQuestion.setUserOwnedId(IdTest);

        quizQuestion.setNo(1);
        quizQuestion.setUserOwnedId(IdTest);
        quizQuestion.setQuiz(quiz);

        quizQuestionService.save(quizQuestion);

        QuizQuestionSearchCriteria criteria = new QuizQuestionSearchCriteria();
        criteria.setQuizId(1L);


        SearchResult<QuizQuestion> searchResult = quizQuestionService.search(criteria);
        assertNotNull(searchResult);
        assertNotNull(searchResult.getValues());
        assertNotNull(searchResult.getValues().get(0));
    }

}
