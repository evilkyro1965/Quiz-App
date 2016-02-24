package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.*;
import com.kyrosoft.quiz.service.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Administrator on 2/18/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ServiceTestConf.xml")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/clean.sql")
})
public abstract class BaseTest {

    protected final String stringTest = "test";

    protected final Long longTest = 1L;

    protected final Double doubleTest = 1.0;

    protected final Boolean boolTest = true;

    protected final Date dateTest = new Date(2015,1,1);

    protected final Long IdTest = 1L;

    protected QuizCategory quizCategory;

    protected Quiz quiz;

    protected User user;

    protected UserQuizAnswer userQuizAnswer;

    @Autowired
    SaveHelper saveHelper;

    @Autowired
    UserService userService;

    @Autowired
    QuizCategoryService quizCategoryService;

    @Autowired
    QuizQuestionService quizQuestionService;

    @Autowired
    UserQuizAnswerService userQuizAnswerService;

    @Autowired
    QuizAnswerService quizAnswerService;

    @Autowired
    QuizService quizService;

    protected <T> void save(T entity) throws ServiceException, DatabasePersistenceException {
        saveHelper.save(entity);
    }

    protected QuizCategory createQuizCategory() throws ServiceException, DatabasePersistenceException {
        QuizCategory quizCategory = new QuizCategory();
        quizCategory.setName(stringTest);

        save(quizCategory);
        this.quizCategory = quizCategory;

        return quizCategory;
    }

    protected Quiz createQuiz() throws ServiceException, DatabasePersistenceException {
        Quiz quiz = new Quiz();
        quiz.setUserOwnedId(IdTest);
        quiz.setCategory(this.quizCategory);
        quiz.setName(stringTest);
        save(quiz);
        this.quiz = quiz;
        return quiz;
    }

    protected User createUser() throws ServiceException, DatabasePersistenceException {
        User user = new User();
        user.setFirstName(stringTest);
        user.setLastName(stringTest);
        user.setUserType(UserType.LECTURER);
        user.setPassword(stringTest);
        user.setEmail(stringTest);
        user.setWorkCompany(stringTest);
        user.setSchool(stringTest);

        save(user);
        this.user = user;
        return user;
    }

    protected UserQuizAnswer createUserQuizAnswer() throws ServiceException, DatabasePersistenceException {
        createUser();
        createQuizCategory();
        createQuiz();

        UserQuizAnswer userQuizAnswer = new UserQuizAnswer();
        userQuizAnswer.setQuiz(this.quiz);
        userQuizAnswer.setUser(this.user);
        userQuizAnswer.setUserOwnedId(this.user.getId());
        save(userQuizAnswer);
        this.userQuizAnswer = userQuizAnswer;
        return userQuizAnswer;
    }

}
