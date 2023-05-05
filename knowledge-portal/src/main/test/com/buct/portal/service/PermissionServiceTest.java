package com.buct.portal.service;

import cn.hutool.core.util.ObjectUtil;
import com.buct.portal.model.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PermissionServiceTest {

    @Autowired
    PermissionService permissionService;

    @Test
    void changeUserPermission() {
        Permission permission = new Permission("1", 0, 0, 0);
        Permission permission1 = permissionService.changeUserPermission(permission);
        assert ObjectUtil.isNotNull(permission1);
    }
}