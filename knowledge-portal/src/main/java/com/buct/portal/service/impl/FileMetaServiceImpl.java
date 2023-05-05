package com.buct.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.buct.common.exception.Asserts;
import com.buct.portal.model.FileMeta;
import com.buct.portal.mapper.FileMetaMapper;
import com.buct.portal.service.FileMetaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
@Service
public class FileMetaServiceImpl extends ServiceImpl<FileMetaMapper, FileMeta> implements FileMetaService {

    @Autowired
    FileMetaMapper fileMetaMapper;
    @Override
    public FileMeta getFileMetaById(String id) {
        FileMeta fileMeta = fileMetaMapper.selectById(id);
        if(ObjectUtil.isNull(fileMeta)) {
            Asserts.fail("Cannot find file meta by id");
        }
        return fileMeta;
    }
}
