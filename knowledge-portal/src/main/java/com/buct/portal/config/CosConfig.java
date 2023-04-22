package com.buct.portal.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CosConfig {

    private String secretId = "AKIDXw6A5saFn8E9yoU1LGzTwCHCH8rQm164";
    private String secretKey = "tXx9D7oLCwUosocGlfRG1iCoepogPuCH";
    private String region = "ap-beijing";

    @Bean
    public COSClient cosClient() {
        COSCredentials cred = new BasicCOSCredentials(this.secretId, this.secretKey);
        Region region = new Region(this.region);
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

}


