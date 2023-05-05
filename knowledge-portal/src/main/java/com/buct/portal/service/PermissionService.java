package com.buct.portal.service;

import com.buct.portal.model.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
@Service
public interface PermissionService extends IService<Permission> {

    Permission changeUserPermission(Permission permission);


}
