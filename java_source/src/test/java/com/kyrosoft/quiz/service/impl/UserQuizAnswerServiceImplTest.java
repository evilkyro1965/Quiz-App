package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.UserQuizAnswer;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.entity.dto.UserQuizAnswerSearchCriteria;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2/20/2016.
 */
public class UserQuizAnswerServiceImplTest extends BaseTest {

    @Test
    public void createUserQuizAnswerTest() throws ServiceException, DatabasePersistenceException {
        createUser();
        createQuizCategory();
        createQuiz();

        UserQuizAnswer userQuizAnswer = new UserQuizAnswer();
        userQuizAnswer.setQuiz(this.quiz);
        userQuizAnswer.setUser(this.user);
        userQuizAnswer.setUserOwnedId(this.user.getId());

        userQuizAnswerService.save(userQuizAnswer);
    }

    @Test
    public void updateUserQuizAnswerTest() throws ServiceException, DatabasePersistenceException {
        createUser();
        createQuizCategory();
        createQuiz();

        UserQuizAnswer userQuizAnswer = new UserQuizAnswer();
        userQuizAnswer.setQuiz(this.quiz);
        userQuizAnswer.setUser(this.user);
        userQuizAnswer.setUserOwnedId(this.user.getId());

        userQuizAnswerService.save(userQuizAnswer);

        UserQuizAnswer updating = new UserQuizAnswer();
        updating.setId(userQuizAnswer.getId());
        updating.setQuiz(this.quiz);
        updating.setUser(this.user);
        updating.setUserOwnedId(this.user.getId());
        updating.setRightAnswer(9);
        updating.setWrongAnswer(1);
        userQuizAnswerService.update(updating);
    }

    @Test
    public void deleteUserQuizAnswerTest() throws ServiceException, DatabasePersistenceException {
        createUser();
        createQuizCategory();
        createQuiz();

        UserQuizAnswer userQuizAnswer = new UserQuizAnswer();
        userQuizAnswer.setQuiz(this.quiz);
        userQuizAnswer.setUser(this.user);
        userQuizAnswer.setUserOwnedId(this.user.getId());

        userQuizAnswerService.save(userQuizAnswer);

        userQuizAnswerService.delete(userQuizAnswer.getId());
    }

    @Test
    public void searchUserQuizAnswerTest() throws ServiceException, DatabasePersistenceException {
        createUser();
        createQuizCategory();
        createQuiz();

        UserQuizAnswer userQuizAnswer = new UserQuizAnswer();
        userQuizAnswer.setQuiz(this.quiz);
        userQuizAnswer.setUser(this.user);
        userQuizAnswer.setUserOwnedId(this.user.getId());

        userQuizAnswerService.save(userQuizAnswer);

        UserQuizAnswerSearchCriteria criteria = new UserQuizAnswerSearchCriteria();
        criteria.setQuizId(this.quiz.getId());
        criteria.setUserId(this.user.getId());

        SearchResult<UserQuizAnswer> searchResult = new SearchResult<UserQuizAnswer>();
        searchResult = userQuizAnswerService.search(criteria);
        assertNotNull(searchResult);
        assertNotNull(searchResult.getValues());
        assertNotNull(searchResult.getValues().get(0));
    }

}
