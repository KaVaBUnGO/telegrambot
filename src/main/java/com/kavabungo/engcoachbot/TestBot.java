package com.kavabungo.engcoachbot;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendSticker;
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
            Message msg = update.getMessage();
            String answer = checkGreeting(msg);
            if (StringUtils.isNotBlank(answer)) {
                sendAnswer(answer, msg.getChatId());
                return;
            }
            checkTime(msg);
        }
    }

    private void checkTime(Message msg) {
        String msgText = msg.getText().toLowerCase();
        if (msgText.toLowerCase().contains("time")) {
            SendSticker sticker = new SendSticker().setSticker("BQADAgADeAcAAlOx9wOjY2jpAAHq9DUC").setChatId(msg.getChatId());
            try {
                sendSticker(sticker);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String checkGreeting(Message msg) {
        String msgText = msg.getText().toLowerCase();
        String msgUserName = msg.getFrom().getUserName() != null ? msg.getFrom().getUserName() : msg.getFrom().getFirstName();
        String answer = StringUtils.EMPTY;
        if (msgText.equals("hi") || msgText.equals("прив") || msgText.equals("привет")) {
            answer = msg.getText() + ", " + msgUserName + "! :3";
        }
        return answer;
    }

    private void sendAnswer(String answer, Long chatId) {
        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(chatId)
                .setText(answer);
        try {
            sendMessage(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
