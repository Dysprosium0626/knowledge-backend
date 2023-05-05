package com.buct.portal.service;

import com.buct.portal.model.FileMeta;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
public interface FileMetaService extends IService<FileMeta> {

    FileMeta getFileMetaById(String id);
}
