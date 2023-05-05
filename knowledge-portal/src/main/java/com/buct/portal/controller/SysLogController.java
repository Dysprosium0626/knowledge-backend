package com.buct.portal.controller;

import com.buct.portal.model.SysLog;
import com.buct.portal.service.SysLogService;
import com.buct.portal.util.R;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 系统日志Controller
 * 
 * @author CL
 *
 */
@RestController
@RequestMapping(value = "/sys/log")
@Api(tags = "日志模块")
@CrossOrigin
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 日志分页查询
     * 
     * @param start  起始页码
     * @param limit  每页数量
     * @param sysLog 系统日志
     * @return
     */
    @GetMapping(value = "page")
    @Operation(summary = "获取所有日志")
    @Parameter(name = "start",description = "分页起始",in = ParameterIn.QUERY)
    @Parameter(name = "limit",description = "分页大小",in = ParameterIn.QUERY)
    public R<Page<SysLog>> page(int start, int limit, SysLog sysLog) {
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>(sysLog);
        queryWrapper.orderByDesc("create_date");
        Page<SysLog> page = sysLogService.page(new Page<>(start, limit), queryWrapper);
        return R.ok(page);
    }

}