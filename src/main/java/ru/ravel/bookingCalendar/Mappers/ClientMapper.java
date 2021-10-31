package ru.ravel.bookingCalendar.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.ravel.bookingCalendar.Models.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Client.builder()
                .id(rs.getLong("id"))
                .telegramId(rs.getLong("telegram_id"))
                .name(rs.getString("name"))
                .build();
    }
}
