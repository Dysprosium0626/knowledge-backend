package com.buct.portal.service;

import com.buct.portal.model.Artifact;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buct.portal.model.User;
import com.buct.portal.model.VO.UserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
public interface ArtifactService extends IService<Artifact> {

    List<Artifact> listArtifacts();
    Artifact getArtifactById(Integer artifactId);

    Boolean addArtifact(Artifact artifact);
    Boolean deleteArtifactById(Integer artifactId);

    Artifact updateArtifactById(Artifact artifact);

}
