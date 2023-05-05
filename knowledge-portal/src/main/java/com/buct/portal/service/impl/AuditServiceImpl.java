package com.buct.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buct.common.exception.Asserts;
import com.buct.portal.mapper.AuditMapper;
import com.buct.portal.model.Audit;
import com.buct.portal.model.FileMeta;
import com.buct.portal.model.VO.ImageVo;
import com.buct.portal.service.AuditService;
import com.buct.portal.service.FileMetaService;
import com.buct.portal.service.ICosFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditServiceImpl extends ServiceImpl<AuditMapper, Audit> implements AuditService {

    @Autowired
    AuditMapper auditMapper;

    @Qualifier("ICosFileImpl")
    @Autowired
    ICosFileService iCosFileService;

    @Autowired
    FileMetaService fileMetaService;

    @Override
    public List<ImageVo> listAllUnauditedImages() {
        QueryWrapper<Audit> queryWrapper = new QueryWrapper<Audit>().eq("is_audit", 0);
        List<Audit> audits = auditMapper.selectList(queryWrapper);
        ArrayList<ImageVo> images = new ArrayList<>();
        for (Audit audit : audits) {
            String id = audit.getFileId();
            FileMeta fileMetaById = fileMetaService.getFileMetaById(id);
            ImageVo image = new ImageVo(id, fileMetaById.getUrl(), audit.getIsAudit(), audit.getResult());
            images.add(image);
        }
        return images;
    }

    @Override
    public Boolean verifyImage(String id, Integer verify) {
        Audit audit = auditMapper.selectById(id);
        audit.setIsAudit(1);
        audit.setResult(verify);
        int updateById = auditMapper.updateById(audit);
        if (updateById == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Audit uploadImage(MultipartFile[] files) {
        String upload = iCosFileService.upload(files);
        FileMeta fileMeta = iCosFileService.addFileMeta(upload);
        Audit audit = new Audit(fileMeta.getId(), 0, 0);
        int insert = auditMapper.insert(audit);
        if (insert == 0) {
            Asserts.fail("Insert audit failed...");
        }
        return audit;
    }
}
