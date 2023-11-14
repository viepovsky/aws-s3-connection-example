package com.viepovsky;

import com.viepovsky.s3.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner myCommandLineRunner(S3Service s3Service) {
        return args -> {
            s3Service.uploadObject("images/foo","Hello World".getBytes());
            byte[] object = s3Service.downloadObject("images/foo");
            System.out.println("Success: " + new String(object));
        };
    }
}
