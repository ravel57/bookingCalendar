package ru.ravel.bookingCalendar.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ravel.bookingCalendar.DAO.ReservationDAO;
import ru.ravel.bookingCalendar.Models.Reservation;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReservationServices {

    private final ReservationDAO reservationDAO;

    public List<Reservation> getActualReservations() {
        return reservationDAO.getActualReservations();
    }

}
