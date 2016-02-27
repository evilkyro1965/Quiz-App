package com.kyrosoft.quiz.service;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.QuizSession;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.entity.dto.UserSessionSearchCriteria;

/**
 * Created by Administrator on 2/20/2016.
 */
public interface QuizSessionService {

    public QuizSession get(long id);

    public QuizSession save(QuizSession userQuizAnswer) throws ServiceException, DatabasePersistenceException;

    public QuizSession update(QuizSession userQuizAnswer) throws ServiceException, DatabasePersistenceException;

    public void delete(Long id) throws ServiceException, DatabasePersistenceException;

    public SearchResult<QuizSession> search(UserSessionSearchCriteria criteria) throws ServiceException;

}
