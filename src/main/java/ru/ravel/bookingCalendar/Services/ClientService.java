package ru.ravel.bookingCalendar.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ravel.bookingCalendar.Controllers.TelegramController;
import ru.ravel.bookingCalendar.DAO.ClientDAO;
import ru.ravel.bookingCalendar.Models.Client;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientDAO clientsDAO;
    @Autowired
    private TelegramController telegram;

    private List<String> waitingRegistration = new ArrayList<>();

    public Client getUserByTelegramId(long telegramId) {
        Client client;
        if (clientsDAO.isUserRegistered(telegramId)) {
            client = clientsDAO.getUserByTelegramId(telegramId);
        } else {
            telegram.sendMessage(telegramId, "аёу, как тебя зовут?");
            String clientName = "";
            client = Client.builder()
                    .name(clientName)
                    .telegramId(telegramId)
                    .build();
            clientsDAO.addClient(client);
            client.setId(clientsDAO.getUserByTelegramId(telegramId).getId());
        }
        return client;
    }


}
