package com.wasacz.planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
public class PlannerApplication extends SpringServletContainerInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PlannerApplication.class, args);
    }

}
