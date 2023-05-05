package com.buct.portal.controller;


import cn.hutool.core.util.ObjectUtil;
import com.buct.common.api.CommonResult;
import com.buct.portal.model.Artifact;
import com.buct.portal.model.VO.UserLoginVo;
import com.buct.portal.model.VO.UserVo;
import com.buct.portal.service.ArtifactService;
import com.buct.portal.service.UserService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
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
@RequestMapping("/artifact")
@Api(tags = "文物模块")
@CrossOrigin
public class ArtifactController {

    @Autowired
    ArtifactService artifactService;

    @GetMapping("/{artifactId}")
    @Operation(summary = "获取文物详情",description = "根据给出的artifactId查询文物信息")
    @Parameter(name = "artifactId",description = "文物Id",in = ParameterIn.PATH)
    public CommonResult getArtifactDetail(@PathVariable Integer artifactId) {
        Artifact artifactById = artifactService.getArtifactById(artifactId);
        return CommonResult.success(artifactById);
    }

    @GetMapping("/all")
    @Operation(summary = "查询所有文物",description = "给出所有文物信息")
    public CommonResult listArtifacts() {
        List<Artifact> artifacts = artifactService.listArtifacts();
        return CommonResult.success(artifacts);
    }

    @PutMapping("/update")
    @Operation(summary = "更新",description = "传入新的文物信息，根据userId进行更新查询")
    public CommonResult updateArtifactDetail(@RequestBody Artifact artifact) {
        Artifact artifact1 = artifactService.updateArtifactById(artifact);
        if(ObjectUtil.isNotNull(artifact1)) {
            return CommonResult.success(true);
        }
        return CommonResult.failed("update failed");
    }

    @DeleteMapping("/{artifactId}")
    @Operation(summary = "删除文物",description = "根据给出的artifactId删除对应的文物")
    @Parameter(name = "artifactId",description = "文物Id",in = ParameterIn.PATH)
    public CommonResult deleteArtifact(@PathVariable Integer artifactId) {
        Boolean delete = artifactService.deleteArtifactById(artifactId);
        if(delete.equals(true)) {
            return CommonResult.success(true);
        }
        return CommonResult.failed("delete failed");
    }

}

