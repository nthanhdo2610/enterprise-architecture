package cs544.member.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public TokenServer tokenServer() {
        // Instantiate and configure the TokenServer object
        return new TokenServer();
    }
}
