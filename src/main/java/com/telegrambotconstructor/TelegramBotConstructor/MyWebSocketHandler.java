package com.telegrambotconstructor.TelegramBotConstructor;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MyWebSocketHandler {

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public String handleTextMessage(String message) {
        System.out.println("WebSocket handleTextMessage called. Received message: " + message);
        return message;
    }
}
