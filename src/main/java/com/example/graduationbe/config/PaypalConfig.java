package com.example.graduationbe.config;


import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaypalConfig {

    @Value("${paypal.client-id}")
    private String clientId;

    @Value("${paypal.client-secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;


//    @Bean
//    public Map<String, String> paypalSdkConfig() {
//        Map<String, String> configMap = new HashMap<>();
//        configMap.put("mode", mode);
//        return configMap;
//    }
//
//    @Bean
//    public OAuthTokenCredential oAuthTokenCredential() {
//        return new OAuthTokenCredential(clientId, clientSecret);
//    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        return new APIContext(clientId, clientSecret, mode);
    }
}
