package ru.ravel.bookingCalendar.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ravel.bookingCalendar.Mappers.ClientMapper;
import ru.ravel.bookingCalendar.Models.Client;

@Repository
public class ClientDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client getUserByTelegramId(long telegramId) {
        return jdbcTemplate.queryForObject(
                "select * from clients\n" +
                        "where telegram_id = ?;",
                new Object[]{telegramId},
                new ClientMapper()
        );
    }

    public boolean isUserRegistered(long telegramID) {
        return jdbcTemplate.queryForObject(
                "select EXISTS(SELECT id FROM clients WHERE telegram_id = ?) as registered;",
                new Object[]{telegramID},
                Long.class
        )== 1 ? true : false;
    }

    //    @Override
    public void addClient(Client client) {
        jdbcTemplate.update(
                "INSERT INTO clients (name, telegram_id) VALUES (?, ?)",
                client.getName(), client.getTelegramId()
        );
    }

}
