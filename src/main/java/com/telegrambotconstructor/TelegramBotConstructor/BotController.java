package com.telegrambotconstructor.TelegramBotConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class BotController {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;  // Добавляем SimpMessagingTemplate

    @PostMapping("/receiveMessage")
    public ResponseEntity<String> receiveMessage(@RequestBody String message) {
        System.out.println("Method receiveMessage called");  // Добавленная строка для логгирования
        System.out.println("Received message: " + message);  // Добавленная строка для логгирования

        Message newMessage = new Message();
        newMessage.setText(message);
        messageRepository.save(newMessage);

        return new ResponseEntity<>("Message received and saved", HttpStatus.OK);
    }

    @GetMapping("/getMessages")
    public ResponseEntity<List<Message>> getMessages() {
        List<Message> messages = (List<Message>) messageRepository.findAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}

