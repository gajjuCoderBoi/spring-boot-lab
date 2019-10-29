package com.ga.musiclabboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Configuration
@Profile("dev")
public class MusicLabBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicLabBootApplication.class, args);
    }

}
