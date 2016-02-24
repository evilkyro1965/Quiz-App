package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.QuizCategory;
import com.kyrosoft.quiz.entity.dto.QuizCategorySearchCriteria;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.service.QuizCategoryService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2/18/2016.
 */
public class QuizServiceTest extends BaseTest {

    @Test
    public void createQuizTest() throws ServiceException, DatabasePersistenceException {

        QuizCategory quizCategory = createQuizCategory();
        Quiz quiz =  new Quiz();
        quiz.setUserOwnedId(IdTest);
        quiz.setCategory(quizCategory);

        quizService.save(quiz);

    }

    @Test
    public void updateQuizTest() throws ServiceException, DatabasePersistenceException {

        QuizCategory quizCategory = createQuizCategory();
        Quiz quiz =  new Quiz();
        quiz.setUserOwnedId(IdTest);
        quiz.setCategory(quizCategory);

        quizService.save(quiz);

        Quiz update =  new Quiz();
        update.setId(quiz.getId());
        update.setUserOwnedId(2L);
        update.setCategory(quizCategory);

        quizService.update(update);

    }

    @Test
    public void deleteQuizTest() throws ServiceException, DatabasePersistenceException {

        QuizCategory quizCategory = createQuizCategory();
        Quiz quiz = new Quiz();
        quiz.setUserOwnedId(IdTest);
        quiz.setCategory(quizCategory);

        quizService.save(quiz);

        quizService.delete(quiz.getId());
    }

    @Test
    public void searchQuizCategoryTest() throws ServiceException, DatabasePersistenceException {

        QuizCategory quizCategory = createQuizCategory();
        Quiz quiz = new Quiz();
        quiz.setUserOwnedId(IdTest);
        quiz.setCategory(quizCategory);
        quizService.save(quiz);

        QuizSearchCriteria criteria = new QuizSearchCriteria();
        SearchResult<Quiz> searchResult = quizService.search(criteria);
        assertNotNull(searchResult);
        assertNotNull(searchResult.getValues());
        assertNotNull(searchResult.getValues().get(0));
    }


}
