package com.kyrosoft.quiz.web.rest;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
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

    @RequestMapping(value = "/create-session", method= RequestMethod.POST)
    public void createSession() {

    }

}
