package com.buct.portal.service.impl;

import com.buct.portal.model.Artifact;
import com.buct.portal.mapper.ArtifactMapper;
import com.buct.portal.service.ArtifactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
@Service
public class ArtifactServiceImpl extends ServiceImpl<ArtifactMapper, Artifact> implements ArtifactService {

    @Override
    public List<Artifact> listArtifacts() {
        return null;
    }

    @Override
    public Artifact getArtifactById(Integer artifactId) {
        return null;
    }

    @Override
    public Boolean addArtifact(Artifact artifact) {
        return null;
    }

    @Override
    public Boolean deleteArtifactById(Integer artifactId) {
        return null;
    }

    @Override
    public Artifact updateArtifactById(Artifact artifact) {
        return null;
    }
}
