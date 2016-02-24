package com.kyrosoft.quiz.web.rest;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.MultipleChoiceQuizAnswer;
import com.kyrosoft.quiz.entity.QuizCategory;
import com.kyrosoft.quiz.entity.dto.QuizCategorySearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.service.QuizAnswerService;
import com.kyrosoft.quiz.service.QuizCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2/22/2016.
 */
@RestController
@RequestMapping("rest/quiz-category")
@Transactional
public class QuizCategoryRestController extends BaseRestController  {

    @Autowired
    QuizCategoryService quizCategoryService;

    @RequestMapping(value = "", method= RequestMethod.POST)
    public SearchResult<QuizCategory> getAll() throws ServiceException,
            DatabasePersistenceException {

        QuizCategorySearchCriteria criteria = new QuizCategorySearchCriteria();
        criteria.setPageSize(ALL_ROW);
        criteria.setSortBy("name");

        SearchResult<QuizCategory> searchResult = quizCategoryService.search(criteria);

        return searchResult;
    }

}
