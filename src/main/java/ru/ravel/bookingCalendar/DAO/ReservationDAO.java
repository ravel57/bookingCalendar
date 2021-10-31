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
                "select reservations.id, clients.name as client_name, cabinet_id, start_time, duration, title, color\n" +
                        "from reservations\n" +
                        "left outer join clients on clients.id = reservations.client_id\n" +
                        "where start_time > DATE(DATE_ADD(now(), INTERVAL -1 DAY));",
                new ReservationMapper()
        );
    }

    public void saveReservation(Reservation reservation, long clientId) {
        jdbcTemplate.update(
                "INSERT INTO reservations (client_id, cabinet_id, start_time, duration, title, color)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?);",
                clientId, reservation.getCabinetId(), reservation.getStartTime(),
                reservation.getDuration(), reservation.getTitle(), reservation.getColor()
        );
    }

}
