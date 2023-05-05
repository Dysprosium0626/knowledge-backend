package com.buct.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buct.portal.model.Artifact;
import com.buct.portal.model.Audit;
import com.buct.portal.model.VO.ImageVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AuditService  extends IService<Audit> {

    List<ImageVo> listAllUnauditedImages();

    Boolean verifyImage(String id, Integer verify);

    Audit uploadImage(MultipartFile[] files);


}
