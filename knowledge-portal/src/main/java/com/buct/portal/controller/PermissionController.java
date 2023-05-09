package com.buct.portal.controller;


import com.buct.common.api.CommonResult;
import com.buct.portal.mapper.CommentMapper;
import com.buct.portal.mapper.PermissionMapper;
import com.buct.portal.model.Permission;
import com.buct.portal.service.PermissionService;
import com.buct.portal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/permission")
@Api(tags = "权限模块")
@CrossOrigin
public class PermissionController {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @PutMapping("/update")
    @Operation(summary = "更新用户的权限", description = "根据给出的userId和Permission更新用户的权限")
    public CommonResult updateUserPermission(@RequestBody Permission permission) {
        Permission permission1 = permissionService.changeUserPermission(permission);
        return CommonResult.success(permission1);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "获取用户的权限", description = "根据给出的userId获取用户的权限")
    public CommonResult getUserPermission(@PathVariable Integer userId) {
        Permission permission1 = permissionService.getPermissionById(userId);
        return CommonResult.success(permission1);
    }

}

