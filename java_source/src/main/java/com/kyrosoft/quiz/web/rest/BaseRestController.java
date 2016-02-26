package com.kyrosoft.quiz.web.rest;

import com.kyrosoft.quiz.entity.User;
import com.kyrosoft.quiz.entity.UserOwnedEntity;
import com.kyrosoft.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    protected String getUploadedImageFilename(String filename, HttpServletRequest request) {
        String ret = "";
        HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        String dir = sc.getRealPath("/");
        ret = dir+"/images/"+filename;
        return ret;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
