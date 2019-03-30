package com.shivangi.telegram.translate;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.shivangi.telegram.aws.AwsComprehendUtil;
import com.shivangi.telegram.aws.AwsTranslateUtil;

public class TranslateBot extends TelegramLongPollingBot {

	@Override
	// Get Bot Username from @Botfather
	public String getBotUsername() {
		return "";
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.getMessage() != null && update.hasMessage() && update.getMessage().hasText()) {
			String txt = update.getMessage().getText().trim();
			long chatId = update.getMessage().getChatId();
			System.out.println("msg recieved - " + txt);
			String languageCode = AwsComprehendUtil.getLanguageCode(txt);
			if(!(languageCode.equalsIgnoreCase("en") || languageCode.equalsIgnoreCase("id"))){
				String translatedTxt =   AwsTranslateUtil.translate(txt, languageCode);
				String reply =   "<b>Translation</b> ✍️\n" + translatedTxt;
				postMsg(chatId, reply);
			}
		}
	}

	@Override
	// Get Bot Token from @Botfather
	public String getBotToken() {
		return "";
	}

	private void postMsg(long chatId, String reply) {
		SendMessage message = new SendMessage().setChatId(chatId).setText(reply).enableHtml(true);
		try {
			execute(message);
			System.out.println("sending msg - " + reply);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
