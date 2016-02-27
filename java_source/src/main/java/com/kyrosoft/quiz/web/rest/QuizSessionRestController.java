package com.kyrosoft.quiz.web.rest;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.*;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.entity.dto.UserSessionSearchCriteria;
import com.kyrosoft.quiz.service.QuizService;
import com.kyrosoft.quiz.service.QuizSessionService;
import com.kyrosoft.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Administrator on 2/20/2016.
 */
@RestController
@RequestMapping("rest/quiz-session")
@Transactional
public class QuizSessionRestController extends BaseRestController {

    @Autowired
    QuizSessionService quizSessionService;

    @Autowired
    QuizService quizService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public QuizSession create(@RequestBody QuizSession quizSession) throws ServiceException,
            DatabasePersistenceException {
        Long userId = getUserLoggedId();
        User user = userService.get(userId);

        quizSession.setUser(user);
        quizSession.setCompleted(false);
        quizSession.setStart(new Date());

        setUserOwnedId(quizSession);
        quizSessionService.save(quizSession);
        return quizSession;
    }


    @RequestMapping(value = "/update/{id}", method= RequestMethod.POST)
    public QuizSession update(@RequestBody QuizSession userQuizAnswer, @PathVariable long id) throws ServiceException,
            DatabasePersistenceException {

        quizSessionService.update(userQuizAnswer);
        return userQuizAnswer;
    }


    @RequestMapping(value = "/delete/{id}", method= RequestMethod.POST)
    public void delete(@PathVariable long id) throws ServiceException,
            DatabasePersistenceException {

        quizSessionService.delete(id);
    }

    @RequestMapping(value = "/get/{id}", method= RequestMethod.POST)
    public QuizSession get(@PathVariable long id) throws ServiceException,
            DatabasePersistenceException {

        QuizSession quizSession = quizSessionService.get(id);
        quizSession.setAnswers(null);
        quizSession.getQuiz().setQuestionList(null);

        return quizSession;
    }


    @RequestMapping(value = "/search", method= RequestMethod.POST)
    public SearchResult<QuizSession> search(@RequestBody UserSessionSearchCriteria criteria) throws ServiceException,
            DatabasePersistenceException {

        Long userId = getUserLoggedId();
        criteria.setUserId(userId);
        SearchResult<QuizSession> searchResult = quizSessionService.search(criteria);

        if(searchResult.getValues()!=null) {
            for(QuizSession quizSession:searchResult.getValues()) {
                quizSession.setAnswers(null);
                quizSession.getQuiz().setQuestionList(null);
            }
        }
        return searchResult;
    }

    @RequestMapping(value = "/search-quiz", method= RequestMethod.POST)
    public SearchResult<Quiz> searchQuiz(@RequestBody QuizSearchCriteria criteria) throws ServiceException,
            DatabasePersistenceException {

        SearchResult<Quiz> searchResult = quizService.search(criteria);

        if(searchResult.getValues()!=null) {
            for(Quiz quiz: searchResult.getValues()){
                quiz.setQuestionList(null);
            }
        }

        return searchResult;
    }

}
