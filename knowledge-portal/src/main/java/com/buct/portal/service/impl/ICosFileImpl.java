package com.buct.portal.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.*;
import com.buct.common.exception.Asserts;
import com.buct.portal.config.CosConfig;
import com.buct.portal.mapper.FileMetaMapper;
import com.buct.portal.model.FileMeta;
import com.buct.portal.service.ICosFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;

@Service
public class ICosFileImpl implements ICosFileService {
    private String bucketName = "knowledge-backend-1301880789";
    private String path = "https://knowledge-backend-1301880789.cos.ap-beijing.myqcloud.com";

    @Resource
    private FileMetaMapper fileMetaMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String upload(MultipartFile[] files) {
        CosConfig cosConfig = new CosConfig();
        COSClient cosClient = cosConfig.cosClient();
        String res = "";
        try {
            for (MultipartFile file : files) {
                String originalFilename = file.getOriginalFilename();
                // 获得文件流
                InputStream inputStream = file.getInputStream();
                //设置文件key
                String filePath = getFileKey(originalFilename);
                ObjectMetadata objectMetadata = new ObjectMetadata();
                // 上传的流如果能够获取准确的流长度，则推荐一定填写 content-length
                // 如果确实没办法获取到，则下面这行可以省略，但同时高级接口也没办法使用分块上传了
                objectMetadata.setContentLength(file.getBytes().length);
                // 上传文件
                cosClient.putObject(new PutObjectRequest(bucketName, filePath, inputStream, null));
                cosClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
                String url = path + "/" + filePath;
                res += url + ",";
            }
            String paths = res.substring(0, res.length() - 1);
            return paths;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("========Shutdown CosClient========");
            cosClient.shutdown();
        }
        return res;
    }
    /**
     * 生成文件路径
     *
     * @return
     */
    private String getFileKey(String originalFileName) {
        //获取后缀名
        String fileType = originalFileName.substring(originalFileName.lastIndexOf("."));
        //以文件后缀来在存储桶中生成文件夹方便文件管理
        String filePath = fileType.substring(1, fileType.length()) + "/";
        //去除文件后缀 替换所有特殊字符
        String fileStr = StrUtil.removeSuffix(originalFileName, fileType).replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5]", "_");
        filePath += new DateTime().toString("yyyyMMddHHmmss") + "_" + fileStr + fileType;
        return filePath;
    }

//    @Override
//    public void delete(String fileName) {
//        CosConfig cosConfig = new CosConfig();
//        cosConfig.cosClient();
//        cosClient.deleteObject(this.bucketName, fileName);
//    }

    @Override
    public FileMeta addFileMeta(String url) {
        int insert = fileMetaMapper.insert(new FileMeta(null, url));
        if (insert != 1) {
            Asserts.fail("Insert fileMeta fails...");
        }
        QueryWrapper<FileMeta> queryWrapper = new QueryWrapper<FileMeta>().eq("url", url);
        List<FileMeta> fileMetas = fileMetaMapper.selectList(queryWrapper);
        return fileMetas.get(0);
    }
}
