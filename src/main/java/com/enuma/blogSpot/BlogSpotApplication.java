package com.enuma.blogSpot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlogSpotApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSpotApplication.class, args);

    }

}
