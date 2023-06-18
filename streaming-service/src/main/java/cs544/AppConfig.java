package cs544;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cs544.token.TokenServer;

@Configuration
public class AppConfig {
    @Bean
    public TokenServer tokenServer() {
        return new TokenServer();
    }
}
