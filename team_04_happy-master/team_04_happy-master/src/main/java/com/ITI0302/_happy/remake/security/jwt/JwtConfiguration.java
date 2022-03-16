package com.ITI0302._happy.remake.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.remake")
@Getter @Setter
public class JwtConfiguration {

    private String secret;
    private int expirationInMs;

    public int getExpirationInMs() {
        return expirationInMs * 60 * 1000;
    }
}
