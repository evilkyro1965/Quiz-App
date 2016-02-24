package com.kyrosoft.quiz.service.impl;

import com.kyrosoft.quiz.DatabasePersistenceException;
import com.kyrosoft.quiz.ServiceException;
import com.kyrosoft.quiz.entity.User;
import com.kyrosoft.quiz.entity.UserType;
import org.junit.Test;

/**
 * Created by Administrator on 2/18/2016.
 */
public class UserServiceTest extends BaseTest {

    @Test
    public void createUserTest() throws ServiceException, DatabasePersistenceException {
        User user = new User();
        user.setFirstName(stringTest);
        user.setLastName(stringTest);
        user.setUserType(UserType.LECTURER);
        user.setPassword(stringTest);
        user.setEmail(stringTest);
        user.setWorkCompany(stringTest);
        user.setSchool(stringTest);

        userService.save(user);
    }

    @Test
    public void updateUserTest() throws ServiceException, DatabasePersistenceException {
        User user = new User();
        user.setFirstName(stringTest);
        user.setLastName(stringTest);
        user.setUserType(UserType.LECTURER);
        user.setPassword(stringTest);
        user.setEmail(stringTest);
        user.setWorkCompany(stringTest);
        user.setSchool(stringTest);

        userService.save(user);

        user.setFirstName("updated");

        userService.update(user);
    }





}
