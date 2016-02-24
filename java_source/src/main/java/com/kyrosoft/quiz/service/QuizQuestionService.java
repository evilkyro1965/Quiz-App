package com.kyrosoft.quiz.service;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.QuizQuestion;
import com.kyrosoft.quiz.entity.dto.QuizQuestionSearchCriteria;
import com.kyrosoft.quiz.entity.dto.QuizSearchCriteria;
import com.kyrosoft.quiz.entity.dto.SearchResult;

/**
 * Created by Administrator on 2/18/2016.
 */
public interface QuizQuestionService {

    public QuizQuestion get(long id);

    public QuizQuestion save(QuizQuestion quizQuestion) throws ServiceException, DatabasePersistenceException;

    public QuizQuestion update(QuizQuestion quizQuestion) throws ServiceException, DatabasePersistenceException;

    public void delete(Long id) throws ServiceException, DatabasePersistenceException;

    public SearchResult<QuizQuestion> search(QuizQuestionSearchCriteria criteria) throws ServiceException;

}
