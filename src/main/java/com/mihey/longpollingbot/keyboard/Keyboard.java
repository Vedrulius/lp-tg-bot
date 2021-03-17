package com.mihey.longpollingbot.keyboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("bot.properties")
public class Keyboard {

    @Value("${bot.button1}")
    private String button1;
    @Value("${bot.button2}")
    private String button2;
    @Value("${bot.button3}")
    private String button3;
    @Value("${bot.button4}")
    private String button4;

    public ReplyKeyboardMarkup getSettingsKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardFSecondRow = new KeyboardRow();

        keyboardFirstRow.add(button1);
        keyboardFirstRow.add(button2);
        keyboardFSecondRow.add(button3);
        keyboardFSecondRow.add(button4);
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardFSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}
