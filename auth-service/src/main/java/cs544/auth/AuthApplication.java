package cs544.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18/errormsg");
        return messageSource;
    }

//    @Bean
//    public OpenAPI usersMicroserviceOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("Your API Title")
//                        .description("Your API Description")
//                        .version("1.0"));
//    }
}