package cz.memsource.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssignmentWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentWebApplication.class, args);
    }

/*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(ENDPOINT_LOGIN_MAPPING).allowedOrigins(CLIENT_URL);
                registry.addMapping(ENDPOINT_PROJECTS_MAPPING).allowedOrigins(CLIENT_URL);
            }
        };
    }
*/
}
