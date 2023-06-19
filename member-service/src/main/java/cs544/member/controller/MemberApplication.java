package cs544.member.controller;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class MemberApplication {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }

    // @Override
    // public void run(String... args) throws Exception {
    //     System.out.println("----------------"+mongoTemplate.getCollectionNames());
    // }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18/errormsg");
        return messageSource;
    }

//     @Bean
//     public OpenAPI usersMicroserviceOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info().title("Your API Title")
//                         .description("Your API Description")
//                         .version("1.0"));
//     }
}