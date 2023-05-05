package com.buct.portal.controller;

import com.buct.common.api.CommonResult;
import com.buct.portal.model.Audit;
import com.buct.portal.model.VO.ImageVo;
import com.buct.portal.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/audit")
@CrossOrigin
public class AuditController {

    @Autowired
    AuditService auditService;

    @GetMapping("/all")
    public CommonResult listAllUnauditedImages() {
        List<ImageVo> imageVos = auditService.listAllUnauditedImages();
        return CommonResult.success(imageVos);
    }

    @PutMapping("/verify")
    public CommonResult verifyImage(@RequestParam("id") String id, @RequestParam("verify") Integer verify) {
        Boolean aBoolean = auditService.verifyImage(id, verify);
        return CommonResult.success(aBoolean);
    }

    @PostMapping("/upload")
    public CommonResult uploadImage(@RequestParam("files") MultipartFile[] files) {
        Audit audit = auditService.uploadImage(files);
        return CommonResult.success(audit);
    }

}
