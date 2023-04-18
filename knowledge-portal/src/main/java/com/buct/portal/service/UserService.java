package com.buct.portal.service;

import com.buct.portal.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buct.portal.model.VO.UserLoginVo;
import com.buct.portal.model.VO.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
public interface UserService extends IService<User> {
    UserVo login(UserLoginVo userLoginVo);
    Boolean registry(UserLoginVo userLoginVo);

    List<UserVo> listUser();
    UserVo getUserById(Integer userId);

    List<UserVo> getUserByUsername(String username);

    Boolean addUser(User user);
    Boolean deleteUserById(Integer userId);

    UserVo updateUserById(UserVo userVo);

}
