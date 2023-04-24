package com.buct.portal.controller;

import com.buct.portal.model.SysLog;
import com.buct.portal.service.SysLogService;
import com.buct.portal.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @RequestMapping(value = "page")
    public R<Page<SysLog>> page(int start, int limit, SysLog sysLog) {
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>(sysLog);
        queryWrapper.orderByDesc("create_date");
        Page<SysLog> page = sysLogService.page(new Page<>(start, limit), queryWrapper);
        return R.ok(page);
    }

}