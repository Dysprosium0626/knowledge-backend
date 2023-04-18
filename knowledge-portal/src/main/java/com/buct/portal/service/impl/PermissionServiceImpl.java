package com.buct.portal.service.impl;

import com.buct.portal.model.Permission;
import com.buct.portal.mapper.PermissionMapper;
import com.buct.portal.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public Permission changeUserPermission(Permission permission) {
        return null;
    }
}
