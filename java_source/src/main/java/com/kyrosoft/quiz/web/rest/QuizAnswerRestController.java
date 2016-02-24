package com.kyrosoft.quiz.web.rest;


import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.MultipleChoiceQuizAnswer;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.service.QuizAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2/20/2016.
 */
@RestController
@RequestMapping("rest/quiz-answer")
@Transactional
public class QuizAnswerRestController extends BaseRestController {

    @Autowired
    QuizAnswerService quizAnswerService;

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public MultipleChoiceQuizAnswer create(@RequestBody MultipleChoiceQuizAnswer quizAnswer) throws ServiceException,
            DatabasePersistenceException {

        setUserOwnedId(quizAnswer);
        quizAnswerService.save(quizAnswer);
        return quizAnswer;
    }

    @RequestMapping(value = "/update/{id}", method= RequestMethod.POST)
    public MultipleChoiceQuizAnswer update(@RequestBody MultipleChoiceQuizAnswer quizAnswer,@PathVariable long id) throws ServiceException,
            DatabasePersistenceException {

        quizAnswerService.update(quizAnswer);
        return quizAnswer;
    }

}
