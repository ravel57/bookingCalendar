package ru.ravel.bookingCalendar.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ravel.bookingCalendar.DAO.ReservationDAO;
import ru.ravel.bookingCalendar.Models.Reservation;

import java.util.List;
import java.util.Random;

@Service
public class ReservationServices {

    @Autowired
    private ReservationDAO reservationDAO;

    public List<Reservation> getActualReservations() {
        return reservationDAO.getActualReservations();
    }

    public List<Reservation> saveReservation() {
        return reservationDAO.getActualReservations();
    }

    public ResponseEntity<Object> generateRandomColor() {
        int red = 0, green = 0, blue = 0, sum = 0;
        int drg, dgb, drb, colorDelta = 0;
        Random random = new Random();
        while (sum < 250 | sum > 500 | colorDelta < 300) {
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            sum = red + green + blue;
            drg = Math.abs(red - green);
            dgb = Math.abs(green - blue);
            drb = Math.abs(red - blue);
            colorDelta = drg + dgb + drb;
        }
        return ResponseEntity.status(HttpStatus.OK).body("rgb(" + red + " " + green + " " + blue + " / 72%)");
    }

}
