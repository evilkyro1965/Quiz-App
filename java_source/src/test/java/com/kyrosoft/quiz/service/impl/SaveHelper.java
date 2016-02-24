package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;

/**
 * Created by Administrator on 11/8/15.
 */
public interface SaveHelper {
    public <T> T save(T entity) throws ServiceException, DatabasePersistenceException;
}
