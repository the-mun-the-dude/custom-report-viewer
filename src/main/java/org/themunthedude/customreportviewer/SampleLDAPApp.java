package org.themunthedude.customreportviewer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Main Application Class - uses Spring Boot. Just run this as a normal Java
 * class to run up a Jetty Server (on http://localhost:8080)
 *
 */
@SpringBootApplication
public class SampleLDAPApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SampleLDAPApp.class, args);
    }

    @Bean
    public WebMvcConfigurerAdapter adapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/login")
                        .setViewName("login");
                registry.addViewController("/viewReports").setViewName("viewReports");
            }
        };
    }

}
