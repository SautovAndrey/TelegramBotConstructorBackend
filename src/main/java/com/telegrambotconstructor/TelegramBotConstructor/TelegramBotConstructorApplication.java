package com.telegrambotconstructor.TelegramBotConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramBotConstructorApplication {

	public static void main(String[] args) {
	SpringApplication.run(TelegramBotConstructorApplication.class, args);
	}

	@Bean
	public MyBot myBot() {
		MyBot bot = new MyBot();
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(bot);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
		return bot;
	}
}

