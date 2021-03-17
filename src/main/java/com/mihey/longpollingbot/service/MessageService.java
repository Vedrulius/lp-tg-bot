package com.mihey.longpollingbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("bot.properties")
public class MessageService {

    @Value("#{${bot.commands}}")
    Map<String, List<String>> map;

    public String getMessage(String message) {
        if (!map.containsKey(message)) {
            return "Invalid command!";
        }

        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(map.get(message).toArray(new String[0]));
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
