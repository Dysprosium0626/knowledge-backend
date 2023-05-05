package com.buct.portal.controller;

import com.buct.common.api.CommonResult;
import com.buct.portal.model.FileMeta;
import com.buct.portal.service.ICosFileService;
import com.buct.portal.service.UserService;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/files")
@CrossOrigin
@Api(tags = "COS模块")
@ApiSupport(author = "dysprosium")
@Slf4j
public class ICosFileController {
    @Resource
    private ICosFileService iCosFileService;

    @Resource
    private UserService userService;

    @PostMapping("/upload")
    public CommonResult test(@RequestParam("files") MultipartFile[] files){
        String upload = iCosFileService.upload(files);
        return CommonResult.success(upload);
    }

    @PostMapping("/add")
    public CommonResult addFileMeta(@RequestParam("url") String url) {
        FileMeta fileMeta = iCosFileService.addFileMeta(url);
        return CommonResult.success(fileMeta);
    }

}
