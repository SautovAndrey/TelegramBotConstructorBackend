package com.telegrambotconstructor.TelegramBotConstructor;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Bot onUpdateReceived called.");
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            sendMessage(chatId, messageText);

            // Сохраняем сообщение в базу данных
            Message newMessage = new Message();
            newMessage.setText(messageText);
            messageRepository.save(newMessage);

            // Отправляем сообщение на фронтенд через WebSocket
            messagingTemplate.convertAndSend("/topic/messages", messageText);
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return "@JoodJoyBot";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    public String getBotToken() {
        return "6346554931:AAEWGKgfACopGAJmsQnDeWkIAZvRRuTrHbI";
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
            System.out.println("Bot send message: " + text + " to chat ID: " + chatId);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}