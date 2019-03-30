package com.shivangi.telegram.translate;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TranslateApp {

	public static void main(String[] args) {

		ApiContextInitializer.init();

		TelegramBotsApi botsApi = new TelegramBotsApi();

		try {
			botsApi.registerBot(new TranslateBot());
			System.out.println("Translate Bot is started!");
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
