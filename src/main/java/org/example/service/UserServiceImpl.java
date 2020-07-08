package org.example.service;

import org.example.dao.UserDao;
import org.example.model.User;

/**
 * @author StanislavR
 */
public class UserServiceImpl {
    private final UserDao userDao;
    private final SecurityService securityService;

    public void assignPassword(User user) throws Exception {
        String passwordMd5 = securityService.md5(user.getPassword());
        user.setPassword(passwordMd5);
        userDao.updateUser(user);
    }

    public UserServiceImpl(UserDao userDao, SecurityService securityService) {
        this.userDao = userDao;
        this.securityService = securityService;
    }
}
