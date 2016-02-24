package com.kyrosoft.quiz.web.rest;

import com.kyrosoft.quiz.entity.User;
import com.kyrosoft.quiz.entity.UserOwnedEntity;
import com.kyrosoft.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Administrator on 11/2/15.
 */
public class BaseRestController {

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private UserService userService;

    protected final int ALL_ROW = 1000000;

    protected String getUserLogged() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username
        return username;
    }

    protected Long getUserLoggedId() {
        String username = getUserLogged();
        User user = userService.get(username);
        return user.getId();
    }

    protected void setUserOwnedId(UserOwnedEntity entity) {
        Long userId = getUserLoggedId();
        entity.setUserOwnedId(userId);
    }

}
