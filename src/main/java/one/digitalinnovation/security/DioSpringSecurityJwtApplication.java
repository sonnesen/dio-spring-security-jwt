package one.digitalinnovation.security;

import one.digitalinnovation.security.security.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SecurityProperties.class})
public class DioSpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(DioSpringSecurityJwtApplication.class, args);
    }

}