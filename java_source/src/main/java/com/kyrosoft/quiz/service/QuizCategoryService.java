package com.kyrosoft.quiz.service;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.QuizCategory;
import com.kyrosoft.quiz.entity.dto.QuizCategorySearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.entity.dto.UserSearchCriteria;

/**
 * Created by Administrator on 2/18/2016.
 */
public interface QuizCategoryService {

    public QuizCategory get(long id);

    public QuizCategory save(QuizCategory quizCategory) throws ServiceException, DatabasePersistenceException;

    public QuizCategory update(QuizCategory quizCategory) throws ServiceException, DatabasePersistenceException;

    public void delete(Long id) throws ServiceException, DatabasePersistenceException;

    public SearchResult<QuizCategory> search(QuizCategorySearchCriteria criteria) throws ServiceException;

}
