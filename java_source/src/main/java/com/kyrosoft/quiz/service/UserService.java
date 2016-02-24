package com.kyrosoft.quiz.service;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.User;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.entity.dto.UserSearchCriteria;

/**
 * Created by Administrator on 2/18/2016.
 */
public interface UserService {

    public User get(long id);

    public User get(String username);

    public User save(User user) throws ServiceException, DatabasePersistenceException;

    public User update(User user) throws ServiceException, DatabasePersistenceException;

    public void delete(Long id) throws ServiceException, DatabasePersistenceException;

    public SearchResult<User> search(UserSearchCriteria criteria) throws ServiceException;
}
