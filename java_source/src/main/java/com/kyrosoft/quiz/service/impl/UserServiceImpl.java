package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.User;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import com.kyrosoft.quiz.entity.dto.UserSearchCriteria;
import com.kyrosoft.quiz.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Administrator on 2/18/2016.
 */
@Repository
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User,UserSearchCriteria>
    implements UserService {

    /**
     * Constructor.
     *
     * @throws IllegalArgumentException if entityClass is null.
     */
    protected UserServiceImpl() {
        super(User.class);
    }

    public User get(long id) {
        User user = entityManager.find(User.class,id);
        return user;
    }

    public User get(String username) {
        String className = ServiceHelper.getClassNameForSearch(User.class);
        Query query = entityManager.createQuery(
                "SELECT u FROM "+className+" u WHERE u.username = :username")
                .setParameter("username", username);
        List<User> result = query.getResultList();
        if(result!=null && result.size()>0) {
            return result.get(0);
        }
        return null;
    }

    public User save(User user) throws ServiceException, DatabasePersistenceException {

        entityManager.persist(user);

        return user;
    }

    public User update(User user) throws ServiceException, DatabasePersistenceException {

        entityManager.merge(user);

        return user;
    }

    public void delete(Long id) throws ServiceException, DatabasePersistenceException {

    }

    public SearchResult<User> search(UserSearchCriteria criteria) throws ServiceException {
        return null;
    }

    @Override
    protected String generateWhereClause(UserSearchCriteria criteria) throws ServiceException {
        return null;
    }

    @Override
    protected void populateQueryParameters(Query query, UserSearchCriteria criteria) throws ServiceException {

    }
}
