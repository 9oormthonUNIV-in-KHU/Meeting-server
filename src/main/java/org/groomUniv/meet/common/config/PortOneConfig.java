package org.groomUniv.meet.common.config;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortOneConfig {

    @Value("${imp.key}")
    private String apiKey;

    @Value("${imp.secret_key}")
    private String secretKey;

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(apiKey, secretKey);
    }
}
