package com.kyrosoft.quiz.web.rest;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2/20/2016.
 */
@RestController
@RequestMapping("rest/quiz")
@Transactional
public class QuizRestController extends BaseRestController {

    @Autowired
    QuizService quizService;

    @RequestMapping(value = "/get/{id}", method= RequestMethod.POST)
    public Quiz get(@PathVariable Long id) throws ServiceException,
            DatabasePersistenceException {

        Quiz quiz = quizService.get(id);
        quiz.setQuestionList(null);
        return quiz;
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public Quiz create(@RequestBody Quiz quiz) throws ServiceException,
            DatabasePersistenceException {

        setUserOwnedId(quiz);
        quizService.save(quiz);
        return quiz;
    }

    @RequestMapping(value = "/update/{id}", method= RequestMethod.POST)
    public Quiz update(@RequestBody Quiz quiz,@PathVariable long id) throws ServiceException,
            DatabasePersistenceException {
        Quiz quizOld = quizService.get(quiz.getId());
        quiz.setUserOwnedId(quizOld.getUserOwnedId());
        quizService.update(quiz);
        return quiz;
    }

    @RequestMapping(value = "/delete/{id}", method= RequestMethod.POST)
    public void delete(@PathVariable long id) throws ServiceException,
            DatabasePersistenceException {

        quizService.delete(id);
    }

    @RequestMapping(value = "/search", method= RequestMethod.POST)
    public SearchResult<Quiz> search(@RequestBody QuizSearchCriteria criteria) throws ServiceException,
            DatabasePersistenceException {
        criteria.setUserId(getUserLoggedId());
        SearchResult<Quiz> searchResult = quizService.search(criteria);

        if(searchResult.getValues()!=null) {
            for(Quiz quiz: searchResult.getValues()){
                quiz.setQuestionList(null);
            }
        }

        return searchResult;
    }

}
