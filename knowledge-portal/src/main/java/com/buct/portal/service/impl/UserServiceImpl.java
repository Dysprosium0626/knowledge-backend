package com.buct.portal.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buct.common.exception.Asserts;
import com.buct.portal.mapper.PermissionMapper;
import com.buct.portal.model.Permission;
import com.buct.portal.model.User;
import com.buct.portal.mapper.UserMapper;
import com.buct.portal.model.VO.UserLoginVo;
import com.buct.portal.model.VO.UserVo;
import com.buct.portal.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public UserVo login(UserLoginVo userLoginVo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq(UserLoginVo.USERNAME, userLoginVo.getUsername());
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size() != 1) {
            Asserts.fail("User does not exist");
        }
        User user = users.get(0);
        if (ObjectUtil.isNull(user)) {
            Asserts.fail("User does not exist");
        }
        boolean checkPassword = BCrypt.checkpw(userLoginVo.getPassword(), user.getPassword());
        if (!checkPassword) {
            Asserts.fail("User name or password wrong");
        }
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public Boolean registry(UserLoginVo userLoginVo) {
        boolean emailExist = this.checkEmailExist(userLoginVo.getEmail());
        if (emailExist) {
            return false;
        }
        User user = new User();
        BeanUtil.copyProperties(userLoginVo, user);
        user.setGender(0);
        user.setDescription("Please change the default description...");
        Boolean addUser = this.addUser(user);
        List<UserVo> userByUsername = getUserByUsername(user.getUsername());
        int insert = permissionMapper.insert(new Permission(userByUsername.get(0).getId(), 0, 0, 0));
        if (insert == 0) {
            Asserts.fail("add user permission failed");
        }
        return addUser;
    }

    @Override
    public List<UserVo> listUser() {
        List<User> users = userMapper.selectList(null);
        ArrayList<UserVo> userVos = new ArrayList<>();
        for (User user : users) {
            UserVo userVo = new UserVo();
            BeanUtil.copyProperties(user, userVo);
            userVos.add(userVo);
        }
        return userVos;
    }

    @Override
    public UserVo getUserById(Integer userId) {
        User user = userMapper.selectById(userId);
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public List<UserVo> getUserByUsername(String username) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(User.USERNAME, username);
        List<User> users = userMapper.selectByMap(map);
        ArrayList<UserVo> userVos = new ArrayList<>();
        for (User user : users) {
            UserVo userVo = new UserVo();
            BeanUtil.copyProperties(user, userVo);
            userVos.add(userVo);
        }
        return userVos;
    }

    @Override
    public Boolean addUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("username", user.getUsername());
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size() >= 1) {
            return false;
        }
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        int insert = userMapper.insert(user);
        if (insert == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteUserById(Integer userId) {
        int deleteById = userMapper.deleteById(userId);
        if (deleteById == 1) {
            return true;
        }
        return false;
    }

    @Override
    public UserVo updateUserById(UserVo userVo) {
        User user = new User();
        BeanUtil.copyProperties(userVo, user);
        int updateById = userMapper.updateById(user);
        if (updateById == 1) {
            return userVo;
        }
        return null;
    }


    private Boolean checkEmailExist(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("email", email);
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size() > 0) {
            return true;
        }
        return false;
    }
}
