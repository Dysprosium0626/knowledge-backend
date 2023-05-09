package com.buct.portal.controller;


import cn.hutool.core.util.ObjectUtil;
import com.buct.common.api.CommonResult;
import com.buct.portal.model.User;
import com.buct.portal.model.VO.UserLoginVo;
import com.buct.portal.model.VO.UserVo;
import com.buct.portal.service.UserService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/users")
@Api(tags = "用户模块")
@ApiSupport(author = "dysprosium")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    @Operation(summary = "获取用户详情",description = "根据给出的userId查询用户信息")
    @Parameter(name = "userId",description = "用户Id",in = ParameterIn.PATH)
    public CommonResult getUserDetail(@PathVariable Integer userId) {
        UserVo userById = userService.getUserById(userId);
        return CommonResult.success(userById);
    }

    @GetMapping("/all")
    @Operation(summary = "查询所有用户",description = "给出所有用户信息")
    public CommonResult listUser() {
        List<UserVo> userVos = userService.listUser();
        return CommonResult.success(userVos);
    }

    @PostMapping("/add")
    @Operation(summary = "新增用户",description = "给出新的信息")
    public CommonResult addUser(@RequestBody User user) {
        Boolean addUser = userService.addUser(user);
        return CommonResult.success(addUser);
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户",description = "传入新的用户信息，根据userId进行更新查询")
    public CommonResult updateUserDetail(@RequestBody UserVo userVo) {
        UserVo userVo1 = userService.updateUserById(userVo);
        if(ObjectUtil.isNotNull(userVo1)) {
            return CommonResult.success(true);
        }
        return CommonResult.failed("update failed");
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "删除用户",description = "根据给出的userId删除对应的用户")
    @Parameter(name = "userId",description = "用户Id",in = ParameterIn.PATH)
    public CommonResult deleteUser(@PathVariable Integer userId) {
        Boolean delete = userService.deleteUserById(userId);
        if(delete.equals(true)) {
            return CommonResult.success(true);
        }
        return CommonResult.failed("delete failed");
    }

    @PostMapping("/login")
    @Operation(summary = "登录",description = "根据用户名和密码进行登录")
    @ApiOperationSupport(ignoreParameters = {"userLoginVo.email"})
    public CommonResult login(@RequestBody UserLoginVo userLoginVo) {
        UserVo login = userService.login(userLoginVo);
        return CommonResult.success(login);
    }

    @PostMapping("/registry")
    @Operation(summary = "注册",description = "根据用户名密码和邮箱进行注册")
    public CommonResult registry(@RequestBody UserLoginVo userLoginVo) {
        Boolean registryUser = userService.registry(userLoginVo);
        return CommonResult.success(registryUser);
    }

}

