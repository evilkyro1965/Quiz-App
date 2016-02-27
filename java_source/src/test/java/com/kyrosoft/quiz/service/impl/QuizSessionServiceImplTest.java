package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.QuizSession;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.entity.dto.UserSessionSearchCriteria;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2/20/2016.
 */
public class QuizSessionServiceImplTest extends BaseTest {

    @Test
    public void createUserQuizAnswerTest() throws ServiceException, DatabasePersistenceException {
        createUser();
        createQuizCategory();
        createQuiz();

        QuizSession userQuizAnswer = new QuizSession();
        userQuizAnswer.setQuiz(this.quiz);
        userQuizAnswer.setUser(this.user);
        userQuizAnswer.setUserOwnedId(this.user.getId());

        quizSessionService.save(userQuizAnswer);
    }

    @Test
    public void updateUserQuizAnswerTest() throws ServiceException, DatabasePersistenceException {
        createUser();
        createQuizCategory();
        createQuiz();

        QuizSession userQuizAnswer = new QuizSession();
        userQuizAnswer.setQuiz(this.quiz);
        userQuizAnswer.setUser(this.user);
        userQuizAnswer.setUserOwnedId(this.user.getId());

        quizSessionService.save(userQuizAnswer);

        QuizSession updating = new QuizSession();
        updating.setId(userQuizAnswer.getId());
        updating.setQuiz(this.quiz);
        updating.setUser(this.user);
        updating.setUserOwnedId(this.user.getId());
        updating.setRightAnswer(9);
        updating.setWrongAnswer(1);
        quizSessionService.update(updating);
    }

    @Test
    public void deleteUserQuizAnswerTest() throws ServiceException, DatabasePersistenceException {
        createUser();
        createQuizCategory();
        createQuiz();

        QuizSession userQuizAnswer = new QuizSession();
        userQuizAnswer.setQuiz(this.quiz);
        userQuizAnswer.setUser(this.user);
        userQuizAnswer.setUserOwnedId(this.user.getId());

        quizSessionService.save(userQuizAnswer);

        quizSessionService.delete(userQuizAnswer.getId());
    }

    @Test
    public void searchUserQuizAnswerTest() throws ServiceException, DatabasePersistenceException {
        createUser();
        createQuizCategory();
        createQuiz();

        QuizSession userQuizAnswer = new QuizSession();
        userQuizAnswer.setQuiz(this.quiz);
        userQuizAnswer.setUser(this.user);
        userQuizAnswer.setUserOwnedId(this.user.getId());

        quizSessionService.save(userQuizAnswer);

        UserSessionSearchCriteria criteria = new UserSessionSearchCriteria();
        criteria.setQuizId(this.quiz.getId());
        criteria.setUserId(this.user.getId());

        SearchResult<QuizSession> searchResult = new SearchResult<QuizSession>();
        searchResult = quizSessionService.search(criteria);
        assertNotNull(searchResult);
        assertNotNull(searchResult.getValues());
        assertNotNull(searchResult.getValues().get(0));
    }

}
