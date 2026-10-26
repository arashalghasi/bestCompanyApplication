package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Tomcat 11.0.27 answers 500 when Accept-Language has more than 10 locales (maxLocaleCount);
    // Chrome sends up to two per configured language (en-US adds en). Spring Boot 4.1 has no property for it.
    @Bean
    WebServerFactoryCustomizer<TomcatServletWebServerFactory> maxLocaleCountCustomizer() {
        return factory -> factory.addConnectorCustomizers(connector -> connector.setMaxLocaleCount(50));
    }
}
