
package com.buct.portal.service;

import com.buct.portal.model.Artifact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtifactServiceTest {

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
