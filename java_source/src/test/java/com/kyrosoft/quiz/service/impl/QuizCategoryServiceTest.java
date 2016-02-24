package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.QuizCategory;
import com.kyrosoft.quiz.entity.dto.QuizCategorySearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2/18/2016.
 */
public class QuizCategoryServiceTest extends BaseTest {

    @Test
    public void createQuizCategoryTest() throws ServiceException, DatabasePersistenceException {

        QuizCategory quizCategory = new QuizCategory();
        quizCategory.setName(stringTest);

        quizCategoryService.save(quizCategory);

    }

    @Test
    public void updateQuizCategoryTest() throws ServiceException, DatabasePersistenceException {

        QuizCategory quizCategory = new QuizCategory();
        quizCategory.setName(stringTest);

        quizCategoryService.save(quizCategory);
        quizCategory.setName("updated");

        quizCategoryService.update(quizCategory);

    }

    @Test
    public void deleteQuizCategoryTest() throws ServiceException, DatabasePersistenceException {

        QuizCategory quizCategory = new QuizCategory();
        quizCategory.setName(stringTest);

        quizCategoryService.save(quizCategory);

        quizCategoryService.delete(quizCategory.getId());

    }

    @Test
    public void searchQuizCategoryTest() throws ServiceException, DatabasePersistenceException {

        QuizCategory quizCategory = new QuizCategory();
        quizCategory.setName(stringTest);

        quizCategoryService.save(quizCategory);

        QuizCategorySearchCriteria criteria = new QuizCategorySearchCriteria();
        SearchResult<QuizCategory> searchResult = quizCategoryService.search(criteria);
        assertNotNull(searchResult);
        assertNotNull(searchResult.getValues());
        assertNotNull(searchResult.getValues().get(0));
    }

}
