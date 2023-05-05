package com.buct.portal.service.impl;

import com.buct.portal.model.Artifact;
import com.buct.portal.service.ArtifactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtifactServiceImplTest {

    @Autowired
    ArtifactService artifactService;

    @Test
    void listArtifacts() {
        List<Artifact> artifacts = artifactService.listArtifacts();
//        for (Artifact artifact : artifacts) {
//            System.out.println(artifact);
//        }
        assertTrue(artifacts.size() >= 1);
    }
}