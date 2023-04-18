package com.buct.portal.service;

import com.buct.portal.model.User;
import com.buct.portal.model.VO.UserLoginVo;
import com.buct.portal.model.VO.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void login() {
        UserLoginVo userLoginVo = new UserLoginVo("dysprosium", "123456", "hello@gmail.com");
        UserVo login = userService.login(userLoginVo);
        System.out.println(login);
    }

    @Test
    void registry() {
        UserLoginVo userLoginVo = new UserLoginVo("dysprosium", "123456", "hello@gmail.com");
        Boolean registry = userService.registry(userLoginVo);
        assertTrue(registry);
    }

    @Test
    void listUser() {
        List<UserVo> userVos = userService.listUser();
        System.out.println(userVos);
        assertTrue(userVos.size() >= 0);
    }

    @Test
    void getUserById() {
        UserVo userById = userService.getUserById(1);
        System.out.println(userById);
    }

    @Test
    void getUserByUsername() {
        List<UserVo> userVos = userService.getUserByUsername("dysprosium");
        for (UserVo userVo : userVos) {
            System.out.println(userVo);
        }
    }

    @Test
    void addUser() {
        User user = new User("dysprosium1", "123456", "dysprosium@gmail.com");
        Boolean addUser = userService.addUser(user);
        assertTrue(addUser);
    }

    @Test
    void deleteUserById() {
        Boolean deleteUserById = userService.deleteUserById(1);
        assertTrue(deleteUserById);
    }

    @Test
    void updateUserById() {
        UserVo user = new UserVo("2", "dysprosium123", "dysprosium@gmail.com");
        UserVo userVo = userService.updateUserById(user);
        System.out.println(userVo);
    }
}