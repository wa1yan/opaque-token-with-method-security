package dev.waiyanhtet.samplecodetest.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "token-resource-server-config")
public class TokenResourceSeverConfig {

    private String tokenCheckUri;
    private String clientId;
    private String clientSecret;
}
