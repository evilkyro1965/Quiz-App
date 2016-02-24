package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.IdentifiableEntity;
import com.kyrosoft.quiz.entity.dto.BaseSearchParameters;
import com.kyrosoft.quiz.entity.dto.SearchResult;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Administrator on 2/18/2016.
 */
public abstract class BaseServiceImpl<T extends IdentifiableEntity,S extends BaseSearchParameters> {

    /**
     * Represents the Java Class object for the entity's class.
     *
     * Non-null.
     *
     * It will be initialized to Class<T> in constructor and will not change afterwards.
     */
    private final Class<T> entityClass;

    /**
     * Represents the EntityManager used to access database persistence.
     *
     * Required. Not null.
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * The class name of the class extends this class.
     */
    protected final String CLASS_NAME = getClass().getName();

    /**
     * Represents the Logger used to perform logging.
     *
     * Optional. If it is not configured, then no logging will be done in the service.
     */
    private Logger logger;

    /**
     * Constructor.
     *
     * @param entityClass
     *            the entity class
     * @throws IllegalArgumentException
     *             if entityClass is null.
     */
    protected BaseServiceImpl(Class<T> entityClass) {
        //ServiceHelper.checkNull(getLogger(), CLASS_NAME + ".BaseGenericService(Class<T> entityClass)",
        //        entityClass, "entityClass");
        this.entityClass = entityClass;
    }

    /**
     * Returns the entityManager.
     *
     * @return the entityManager
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Returns the logger.
     *
     * @return the logger
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Sets the logger.
     *
     * @param logger
     *            the logger to set
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * This method is used to search entities based on the search criteria.
     *
     * @param criteria
     *            the search criteria
     * @return the search result
     * @throws IllegalArgumentException
     *             if criteria is null; criteria.pageNumber < 0; criteria.pageSize < 0; criteria.pageNumber > 0 and
     *             criteria.pageSize <= 0; the criteria has some fields which are ids and they are not positive
     * @throws ServiceException
     *             if any other error occurred during the operation
     */
    public SearchResult<T> search(S criteria) throws ServiceException {
        final String signature = CLASS_NAME + ".search(S criteria)";
        ServiceHelper.checkCriteria(getLogger(), signature, criteria, "criteria");

        // Set default sorting option
        ServiceHelper.setSortByAndSortType(criteria, entityClass);

        String className = ServiceHelper.getClassNameForSearch(entityClass);

        try {
            StringBuffer sb = new StringBuffer("SELECT e FROM ").append(className).append(" e ");
            // Generate and append WHERE clause
            sb.append(generateWhereClause(criteria));
            // Append ORDER BY clause
            sb.append(" ORDER BY ").append(criteria.getSortBy()).append(" ").append(criteria.getSortType().name());

            // Create query
            TypedQuery<T> query = getEntityManager().createQuery(sb.toString(), entityClass);

            // Populate query parameters
            populateQueryParameters(query, criteria);

            // set paging options
            if (criteria.getPageNumber() > 0) {
                query.setMaxResults(criteria.getPageSize());
                query.setFirstResult((criteria.getPageNumber() - 1) * criteria.getPageSize());
            }

            // Execute the query
            List<T> entities = query.getResultList();

            SearchResult<T> result = new SearchResult<T>();
            result.setValues(entities);

            if (criteria.getPageNumber() > 0) {
                sb.delete(0, sb.length());
                // get total page count
                sb.append("SELECT COUNT(e) FROM ").append(className).append(" e ");
                sb.append(generateWhereClause(criteria));

                // Create query
                TypedQuery<Long> countQuery = getEntityManager().createQuery(sb.toString(), Long.class);

                populateQueryParameters(countQuery, criteria);

                int totalCount = countQuery.getSingleResult().intValue();
                int totalPageCount = (totalCount + criteria.getPageSize() - 1) / criteria.getPageSize();
                result.setTotal(totalCount);
                result.setTotalPages(totalPageCount);
                result.setPageSize(criteria.getPageSize());
                result.setPageNumber(criteria.getPageNumber());
            } else {
                result.setTotal(entities.size());
                result.setTotalPages(1);
                result.setPageSize(entities.size());
                result.setPageNumber(1);
            }

            // set sortBy and sortType for result
            result.setSortBy(criteria.getSortBy());
            result.setSortType(criteria.getSortType());

            ServiceHelper.logAction(getLogger(), signature, entityClass.getSimpleName()
                    + " entities with criteria " + ServiceHelper.toString(criteria)
                    + " are searched. The JSON representation is " + ServiceHelper.toString(result));
            return result;
        } catch (IllegalStateException e) {
            throw ServiceHelper.logException(getLogger(), signature, new ServiceException(
                    "Entity manager is closed when searching entities", e));
        } catch (ServiceException e) {
            throw ServiceHelper.logException(getLogger(), signature, new ServiceException(
                    "Persistence erroroccurs when searching entities", e));
        }
    }

    /**
     * Generate JPA WHERE clause based on search criteria.
     *
     * @param criteria
     *            the criteria
     * @return the generated WHERE clause.
     * @throws ServiceException
     *             if any error occurred during the operation
     */
    protected abstract String generateWhereClause(S criteria) throws ServiceException;

    /**
     * Populate query parameters.
     *
     *
     * @param query
     *            the JPA query
     * @param criteria
     *            the criteria
     * @throws ServiceException
     *             if any error occurred during the operation
     */
    protected abstract void populateQueryParameters(Query query, S criteria) throws ServiceException;
}
