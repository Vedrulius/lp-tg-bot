package com.mihey.longpollingbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class LongpollingbotApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(LongpollingbotApplication.class, args);
    }

}
