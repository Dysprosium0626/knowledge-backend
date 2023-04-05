package com.buct.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.buct")
@MapperScan("com.buct.portal.mapper")
public class KnowledgePortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(KnowledgePortalApplication.class, args);
    }

}
