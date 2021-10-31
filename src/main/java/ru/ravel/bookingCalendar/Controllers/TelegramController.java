package ru.ravel.bookingCalendar.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.ravel.bookingCalendar.Models.Client;
import ru.ravel.bookingCalendar.Models.Reservation;
import ru.ravel.bookingCalendar.Services.CabinetService;
import ru.ravel.bookingCalendar.Services.ClientService;
import ru.ravel.bookingCalendar.Services.ReservationServices;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class TelegramController extends TelegramLongPollingBot {

    final String botName = "Bot";
    final String token = "1859807383:AAEM-u6y9jRgIk1QXkNFOJWrn6ZWqHNNvjU";

    @Autowired
    private ClientService clientService;
    @Autowired
    private CabinetService cabinetService;
    @Autowired
    private ReservationServices reservationServices;

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
        Client client = clientService.getUserByTelegramId(update.getMessage().getFrom().getId());
        if (update.getMessage().hasText()) {
            reservationServices.saveReservation(update.getMessage().getText(), client);
        }
    }

    public void sendMessage(long telegramId, String text) {
        try {
            execute(SendMessage.builder()
                    .chatId(Long.toString(telegramId))
                    .text(text)
                    .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
