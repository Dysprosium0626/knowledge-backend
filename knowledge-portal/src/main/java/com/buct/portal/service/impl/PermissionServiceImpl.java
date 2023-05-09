package com.buct.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.buct.common.exception.Asserts;
import com.buct.portal.model.Permission;
import com.buct.portal.mapper.PermissionMapper;
import com.buct.portal.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Permission changeUserPermission(Permission permission) {
        int updateById = permissionMapper.updateById(permission);
        if (updateById == 0){
            Asserts.fail("update user permission failed...");
        }
        return permission;
     }

    @Override
    public Permission getPermissionById(Integer userId) {
        Permission permission = permissionMapper.selectById(userId);
        if (ObjectUtil.isNull(permission)) {
            Asserts.fail("User permission does not exist...");
        }
        return permission;
    }
}
