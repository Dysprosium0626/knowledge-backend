package com.buct.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.buct.common.exception.Asserts;
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
        List<Artifact> artifacts = artifactMapper.selectList(null);
        // 直接返回获取的列表
        return artifacts;
    }

    @Override
    public Artifact getArtifactById(Integer artifactId) {
        // 调用artifactMapper的selectById方法
        Artifact artifact = artifactMapper.selectById(artifactId);
        // 直接返回获取的Artifact对象
        if (ObjectUtil.isNull(artifact)) {
            Asserts.fail("artifact id is wrong...");
        }
        return artifact;
    }

    @Override
    public Boolean addArtifact(Artifact artifact) {
        // 根据artifact.id查询Artifact
        Artifact artifact1 = artifactMapper.selectById(artifact.getId());
        // 如果id存在，那么返回false
        if (ObjectUtil.isNotNull(artifact1)) {
            Asserts.fail("artifact id already exists...");
        }
        // 如果id不存在，调用artifactMapper的insert方法
        int insert = artifactMapper.insert(artifact);
        // 判断返回值是0还是1，返回对应的bool值
        return insert == 1;
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
