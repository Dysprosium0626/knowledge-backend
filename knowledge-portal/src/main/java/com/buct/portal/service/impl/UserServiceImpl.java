package com.buct.portal.service.impl;

import com.buct.portal.model.User;
import com.buct.portal.mapper.UserMapper;
import com.buct.portal.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
