package com.buct.portal.service;

import com.qcloud.cos.model.PutObjectResult;
import com.buct.portal.model.FileMeta;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//在此编写你要实现的功能方法
@Service
public interface ICosFileService {
    //文件批量上传
    public String upload(MultipartFile[] files);
    //文件删除
//    void delete(String fileName);

    FileMeta addFileMeta(String url);
}