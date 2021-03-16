package com.mihey.longpollingbot.bot;

import com.mihey.longpollingbot.keyboard.Keyboard;
import com.mihey.longpollingbot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.id}")
    private int id;

    private final Keyboard keyboard;
    private final MessageService messageService;

    @Autowired
    public TelegramBot(Keyboard keyboard, MessageService messageService) {
        this.keyboard = keyboard;
        this.messageService = messageService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!(update.getMessage().getFrom().getId() == id) ||
                update.getMessage() == null ||
                !(update.getMessage().hasText())) {
            return;
        }
        long chat_id = update.getMessage().getChatId();
        String message = update.getMessage().getText();
        sendMessage(chat_id, messageService.getMessage(message));
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(false);
        message.setReplyMarkup(keyboard.getSettingsKeyboard());
        message.setChatId(chatId)
                .setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
