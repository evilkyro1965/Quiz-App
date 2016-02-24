package com.kyrosoft.quiz.web.rest;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.*;
import com.kyrosoft.quiz.service.*;
import com.kyrosoft.quiz.service.impl.SaveHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseTest {

    protected final String stringTest = "test";

    protected final Long longTest = 1L;

    protected final Double doubleTest = 1.0;

    protected final Boolean boolTest = true;

    protected final Date dateTest = new Date(2015,1,1);

    protected final Long IdTest = 1L;

    protected QuizCategory quizCategory;

    protected Quiz quiz;

    protected QuizQuestion quizQuestion;

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

    protected QuizQuestion createQuizQuestion() throws ServiceException, DatabasePersistenceException {
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

        save(quizQuestion);
        this.quizQuestion = quizQuestion;
        return quizQuestion;
    }

    protected UserQuizAnswer createUserQuizAnswer() throws ServiceException, DatabasePersistenceException {
        UserQuizAnswer userQuizAnswer = new UserQuizAnswer();
        userQuizAnswer.setQuiz(this.quiz);
        userQuizAnswer.setUser(this.user);
        userQuizAnswer.setUserOwnedId(this.user.getId());
        save(userQuizAnswer);
        this.userQuizAnswer = userQuizAnswer;
        return userQuizAnswer;
    }


}
