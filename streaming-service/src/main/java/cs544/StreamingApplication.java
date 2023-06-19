package cs544;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StreamingApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(StreamingApplication.class, args);
    }

//    @Bean
//    public OpenAPI usersMicroserviceOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("Your API Title")
//                        .description("Your API Description")
//                        .version("1.0"));
//    }
}