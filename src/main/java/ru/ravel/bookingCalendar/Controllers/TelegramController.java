package ru.ravel.bookingCalendar.Controllers;

import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Controller
public class TelegramController extends TelegramLongPollingBot {

    final String botName = "fadmBronBot";
    final String token ="1859807383:AAGw7TLN1507D_VymzqHRdwxqzmtufRA94E";

    public TelegramController() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            this.getOptions().setMaxThreads(10);
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
//        User user = update.getMessage().getFrom();
//        Client client = clients.getClientByTelegramUser(user);
//        if (update.getMessage().hasText()) {
//            Message message = Message.builder()
//                    .text(update.getMessage().getText())
//                    .clientId(client.getId())
//                    .date(new java.util.Date((long) update.getMessage().getDate() * 1000))
//                    .messageType("client")
//                    .build();
//            messages.saveClientMessage(message);
//            message.setId(messages.getClientsMessagesCount(client) - 1);
//            messages.markChatUnread(message);
//            messages.sendMessagesToFront(message);
//        }
    }

    public void sendMessage() {
        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId();
//        sendMessage.setText(message.getText());
        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
