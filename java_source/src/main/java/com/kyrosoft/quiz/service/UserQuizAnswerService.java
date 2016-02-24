package com.kyrosoft.quiz.service;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.UserQuizAnswer;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.entity.dto.UserQuizAnswerSearchCriteria;

/**
 * Created by Administrator on 2/20/2016.
 */
public interface UserQuizAnswerService {

    public UserQuizAnswer get(long id);

    public UserQuizAnswer save(UserQuizAnswer userQuizAnswer) throws ServiceException, DatabasePersistenceException;

    public UserQuizAnswer update(UserQuizAnswer userQuizAnswer) throws ServiceException, DatabasePersistenceException;

    public void delete(Long id) throws ServiceException, DatabasePersistenceException;

    public SearchResult<UserQuizAnswer> search(UserQuizAnswerSearchCriteria criteria) throws ServiceException;

}
