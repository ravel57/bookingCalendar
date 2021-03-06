package ru.ravel.bookingCalendar.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.ravel.bookingCalendar.Models.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationMapper implements RowMapper<Reservation> {
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Reservation.builder()
                .id(rs.getLong("id"))
                .clientName(rs.getString("client_name"))
                .cabinetId(rs.getLong("cabinet_id"))
                .startTime(rs.getTimestamp("start_time"))
                .duration(rs.getLong("duration"))
                .title(rs.getString("title"))
                .color(rs.getString("color"))
                .build();
    }
}
