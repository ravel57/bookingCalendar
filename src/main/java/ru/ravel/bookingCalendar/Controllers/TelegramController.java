package ru.ravel.bookingCalendar.Controllers;

import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Controller
public class TelegramController extends TelegramLongPollingBot {

    final String botName = "bot";
    final String token ="1859807383:AAG7qGnArHk8IssCWKjG2YDXqfubYqTj7yk";

    public TelegramController() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            this.getOptions().setMaxThreads(5);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    public void sendMessage() {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId("");
            sendMessage.setText("");
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
