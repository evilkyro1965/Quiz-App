package com.kyrosoft.quiz.service;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.Quiz;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;

/**
 * Created by Administrator on 2/18/2016.
 */
public interface QuizService {

    public Quiz get(long id);

    public Quiz save(Quiz quiz) throws ServiceException, DatabasePersistenceException;

    public Quiz update(Quiz quiz) throws ServiceException, DatabasePersistenceException;

    public void delete(Long id) throws ServiceException, DatabasePersistenceException;

    public SearchResult<Quiz> search(QuizSearchCriteria criteria) throws ServiceException;

}
