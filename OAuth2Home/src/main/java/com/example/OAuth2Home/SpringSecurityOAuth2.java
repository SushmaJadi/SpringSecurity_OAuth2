package com.example.OAuth2Home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


@SpringBootApplication
public class SpringSecurityOAuth2 {
    public static void main(String[] args) {

        System.out.println("hello world");
        ConfigurableApplicationContext context = SpringApplication.run(SpringSecurityOAuth2.class, args);
        context.getBean(DispatcherServlet.class).setDetectAllHandlerExceptionResolvers(true);



    }
}
