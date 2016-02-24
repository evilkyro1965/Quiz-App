package com.kyrosoft.quiz.web.rest;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.UserQuizAnswer;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.entity.dto.UserQuizAnswerSearchCriteria;
import com.kyrosoft.quiz.service.QuizService;
import com.kyrosoft.quiz.service.UserQuizAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2/20/2016.
 */
@RestController
@RequestMapping("rest/user-quiz-answer")
@Transactional
public class UserQuizAnswerRestController extends BaseRestController {

    @Autowired
    UserQuizAnswerService userQuizAnswerService;

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public UserQuizAnswer create(@RequestBody UserQuizAnswer userQuizAnswer) throws ServiceException,
            DatabasePersistenceException {

        setUserOwnedId(userQuizAnswer);
        userQuizAnswerService.save(userQuizAnswer);
        return userQuizAnswer;
    }


    @RequestMapping(value = "/update/{id}", method= RequestMethod.POST)
    public UserQuizAnswer update(@RequestBody UserQuizAnswer userQuizAnswer,@PathVariable long id) throws ServiceException,
            DatabasePersistenceException {

        userQuizAnswerService.update(userQuizAnswer);
        return userQuizAnswer;
    }


    @RequestMapping(value = "/delete/{id}", method= RequestMethod.POST)
    public void delete(@PathVariable long id) throws ServiceException,
            DatabasePersistenceException {

        userQuizAnswerService.delete(id);
    }


    @RequestMapping(value = "/search", method= RequestMethod.POST)
    public SearchResult<UserQuizAnswer> search(@RequestBody UserQuizAnswerSearchCriteria criteria) throws ServiceException,
            DatabasePersistenceException {

        SearchResult<UserQuizAnswer> searchResult = userQuizAnswerService.search(criteria);

        return searchResult;
    }

}
