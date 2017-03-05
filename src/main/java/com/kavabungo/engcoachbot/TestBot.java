package com.kavabungo.engcoachbot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by User on 05.03.2017.
 */
public class TestBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getText());
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (update.hasChannelPost()) {
            Message channelPost = update.getChannelPost();
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(channelPost.getChatId())
                    .setText("[EngCoachBot]: " + channelPost.getText());
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "my test bot";
    }

    @Override
    public String getBotToken() {
        return "351123570:AAEovDNuuzik2u1buLOMm_K_evIGQs9pUgA";
    }
}
