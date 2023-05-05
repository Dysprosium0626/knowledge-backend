package com.buct.portal.controller;

import com.buct.common.api.CommonResult;
import com.buct.portal.model.Audit;
import com.buct.portal.model.VO.ImageVo;
import com.buct.portal.service.AuditService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/audit")
@Api(tags = "审核模块")
@CrossOrigin
public class AuditController {

    @Autowired
    AuditService auditService;

    @GetMapping("/all")
    @Operation(summary = "查询所有未审核的图片")
    public CommonResult listAllUnauditedImages() {
        List<ImageVo> imageVos = auditService.listAllUnauditedImages();
        return CommonResult.success(imageVos);
    }

    @PutMapping("/verify")
    @Operation(summary = "审核图片", description = "根据给出的id和verify字段决定审核是否通过，0为未通过，1为通过")
    public CommonResult verifyImage(@RequestParam("id") String id, @RequestParam("verify") Integer verify) {
        Boolean aBoolean = auditService.verifyImage(id, verify);
        return CommonResult.success(aBoolean);
    }

    @PostMapping("/upload")
    @Operation(summary = "上传图片")
    public CommonResult uploadImage(@RequestParam("files") MultipartFile[] files) {
        Audit audit = auditService.uploadImage(files);
        return CommonResult.success(audit);
    }

}
