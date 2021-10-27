package ru.ravel.bookingCalendar.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ravel.bookingCalendar.Mappers.ReservationMapper;
import ru.ravel.bookingCalendar.Models.Reservation;

import java.util.List;

@Repository
public class ReservationDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Reservation> getActualReservations() {
        return jdbcTemplate.query(
                "select * from reservations;",
                new ReservationMapper()
        );
    }

}
