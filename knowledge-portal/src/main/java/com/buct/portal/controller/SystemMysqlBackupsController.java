package com.buct.portal.controller;

import com.buct.common.api.CommonResult;
import com.buct.common.exception.Asserts;
import com.buct.portal.model.SystemMysqlBackups;
import com.buct.portal.service.SystemMysqlBackupsService;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Software：IntelliJ IDEA 2021.2 x64
 * Author: https://www.mobaijun.com
 * Date: 2021/9/16 14:45
 * ClassName:SystemMysqlBackupsController
 * 类描述： MySQL数据备份接口
 */
@RestController
@Api(description = "MySQL数据备份模块")
@ApiSupport(author = "dysprosium")
@RequestMapping(value = "/mysql")
public class SystemMysqlBackupsController {

    /**
     * 数据库用户名
     */
    @Value("${spring.datasource.username}")
    private String userName;

    /**
     * 数据库密码
     */
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * 数据库url
     */
    @Value("${spring.datasource.url}")
    private String url;

    /**
     * Linux数据库备份地址
     */
    @Value("${spring.datasource.linux-path}")
    private String linuxPath;

    @Autowired
    private SystemMysqlBackupsService systemMysqlBackupsService;

    @ApiOperation(value = "获取所有备份数据列表")
    @GetMapping("/backupsList")
    public CommonResult backupsList() {
        List<SystemMysqlBackups> systemMysqlBackups = systemMysqlBackupsService.selectBackupsList();
        return CommonResult.success(systemMysqlBackups);
    }

    @ApiOperation(value = "MySQL备份")
    @PostMapping("/mysqlBackups")
    @Operation(summary = "MySQL备份",description = "备份当前MySQL数据库")
    public CommonResult mysqlBackups() {

        // 获取操作系统名称
        String osName = System.getProperty("os.name").toLowerCase();
        String path = this.linuxPath;

        // 数据库用户名
        String userName = this.userName;
        // 数据库密码
        String password = this.password;
        // 数据库地址
        String url = this.url;
        // 调用备份
        Object systemMysqlBackups = systemMysqlBackupsService.mysqlBackups(path, url, userName, password);
        return CommonResult.success(systemMysqlBackups);
    }

    @ApiOperation(value = "恢复数据库")
    @PutMapping("/rollback/{id}")
    @Operation(summary = "恢复数据库用户",description = "根据给出的id恢复数据库到指定版本")
    @Parameter(name = "id",description = "备份id",in = ParameterIn.PATH)
    public Object rollback(@PathVariable Integer id) {
        if (id == null) {
            Asserts.fail("id不能为null，请重新尝试！");
        }
        // 数据库用户名
        String userName = this.userName;
        // 数据库密码
        String password = this.password;
        // 根据id查询查询已有的信息
        SystemMysqlBackups smb = systemMysqlBackupsService.selectListId(Long.valueOf(id));
        // 恢复数据库
        Object rollback = systemMysqlBackupsService.rollback(smb, userName, password);
        // 更新操作次数
        systemMysqlBackupsService.updateById(smb);
        return CommonResult.success(rollback);
    }
}