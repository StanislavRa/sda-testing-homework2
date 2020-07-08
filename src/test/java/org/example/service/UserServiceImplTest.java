package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

/**
 * @author StanislavR
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private SecurityService securityService;

    @Test
    void shouldAssignPasswordAndUpdateUser() throws Exception {
        User user = new User();
        String userPassword = "userPass";
        user.setPassword(userPassword);

        when(securityService.md5(userPassword)).thenReturn("hashed " + userPassword);

        UserServiceImpl userService = new UserServiceImpl(userDao, securityService);

        userService.assignPassword(user);

        Assertions.assertEquals("hashed " + userPassword, user.getPassword());
        Mockito.verify(userDao, Mockito.times(1)).updateUser(user);
    }
}
