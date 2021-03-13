package com.mihey.longpollingbot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.command}")
    private String command;
    @Value("${bot.commandName}")
    private String commandName;
    @Value("${bot.button1}")
    private String button1;
    @Value("${bot.button2}")
    private String button2;
    @Value("${bot.button3}")
    private String button3;

    @Override
    public void onUpdateReceived(Update update) {
        if (!(update.getMessage().getFrom().getId() == 425652971)) return;
        String message = getMessage();
        long chat_id = update.getMessage().getChatId();
        if (update.getMessage() != null && update.getMessage().hasText() &&
                update.getMessage().getText().equals(commandName)) {
            sendMessage(chat_id, message);
        } else {
            sendMessage(chat_id, "wrong command!");
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    private String getMessage() {
        StringBuilder output = new StringBuilder();

        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setReplyMarkup(getSettingsKeyboard());
        message.setChatId(chatId)
                .setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup getSettingsKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(0, button1);
        keyboardFirstRow.add(1, button2);
        keyboardFirstRow.add(2, button3);
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}
