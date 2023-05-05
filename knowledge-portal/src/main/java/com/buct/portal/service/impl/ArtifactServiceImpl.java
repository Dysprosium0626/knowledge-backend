package com.buct.portal.service.impl;

import com.buct.portal.model.Artifact;
import com.buct.portal.mapper.ArtifactMapper;
import com.buct.portal.service.ArtifactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
@Service
public class ArtifactServiceImpl extends ServiceImpl<ArtifactMapper, Artifact> implements ArtifactService {

    @Autowired
    ArtifactMapper artifactMapper;

    @Override
    public List<Artifact> listArtifacts() {
        // 调用artifactMapper的selectList方法

        // 直接返回获取的列表

        return null;
    }

    @Override
    public Artifact getArtifactById(Integer artifactId) {
        // 调用artifactMapper的selectById方法

        // 直接返回获取的Artifact对象

        return null;
    }

    @Override
    public Boolean addArtifact(Artifact artifact) {
        // 根据artifact.id查询Artifact

        // 如果id存在，那么返回false

        // 如果id不存在，调用artifactMapper的insert方法

        // 判断返回值是0还是1，返回对应的bool值

        return null;
    }

    @Override
    public Boolean deleteArtifactById(Integer artifactId) {
        // 调用artifactMapper的deleteById方法

        // 判断返回值是0还是1，返回对应的bool值
        return null;
    }

    @Override
    public Artifact updateArtifactById(Artifact artifact) {
        // 调用artifactMapper的updateById方法

        // 判断返回值是0还是1，返回对应的bool值

        return null;
    }
}
