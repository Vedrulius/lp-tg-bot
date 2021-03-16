package com.mihey.longpollingbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("bot.properties")
public class MessageService {

    @Value("#{${bot.commands}}")
    Map<String, String> map;

    public String getMessage(String message) {

        if (!map.containsKey(message)) {
            return "Invalid command!";
        }
        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(map.get(message));
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
