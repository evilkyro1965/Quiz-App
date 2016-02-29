package com.kyrosoft.quiz.web.rest;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.User;
import com.kyrosoft.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2/22/2016.
 */
@RestController
@RequestMapping("rest/login")
@Transactional
public class LoginRestController extends BaseRestController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/get-logged-user", method= RequestMethod.POST)
    public User createSession() {
        Long userId = getUserLoggedId();
        User user = userService.get(userId);
        return user;
    }

}
