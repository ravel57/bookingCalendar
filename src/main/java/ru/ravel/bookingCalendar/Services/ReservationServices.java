package ru.ravel.bookingCalendar.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.ravel.bookingCalendar.DAO.ReservationDAO;
import ru.ravel.bookingCalendar.Models.ReserveCabinet;
import ru.ravel.bookingCalendar.Models.ReserveDay;
import ru.ravel.bookingCalendar.Models.Reservation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReservationServices {

    @Autowired
    private ReservationDAO reservationDAO;

    public List<ReserveDay> getActualReservations() throws ParseException {
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        List<Reservation> actualReservations = reservationDAO.getActualReservations();
        Map<String, List<Reservation>> reservationsSplitByDaysMap = actualReservations.stream().collect(
                Collectors.groupingBy(w -> new StringBuilder()
                        .append(w.getStartTime().getDate())
                        .append("-")
                        .append(w.getStartTime().getMonth() + 1)
                        .append("-")
                        .append(w.getStartTime().getYear() + 1900)
                        .toString())
        );
        List<ReserveDay> reservationsSplitByDays = new ArrayList<>();
        Object[] datesOfSplitedReservations = reservationsSplitByDaysMap.keySet().toArray();
        Object[] valuesOfSplitedReservations = reservationsSplitByDaysMap.values().toArray();

        for (int i = 0; i < reservationsSplitByDaysMap.size(); i++) {
            List<Reservation> day = (List<Reservation>) valuesOfSplitedReservations[i];
            Map<Long, List<Reservation>> reservationsSplitByCabinets = day.stream().collect(
                    Collectors.groupingBy(w -> w.getCabinetId())
            );
            List<ReserveCabinet> reserveCabinetsInThisDay = new ArrayList<>();
            Object[] cabinetNumbers = reservationsSplitByCabinets.keySet().toArray();
            Object[] reservationsInCabinets = reservationsSplitByCabinets.values().toArray();
            for (int j = 0; j < reservationsSplitByCabinets.size(); j++) {
                reserveCabinetsInThisDay.add(
                        ReserveCabinet.builder()
                                .cabinetId((long) cabinetNumbers[j])
                                .reserved((List<Reservation>) reservationsInCabinets[j])
                                .build()
                );
            }
            reservationsSplitByDays.add(ReserveDay.builder()
                    .date(dateFormatter.parse((String) datesOfSplitedReservations[i]))
                    .cabinets(reserveCabinetsInThisDay)
                    .build());
        }
        reservationsSplitByDays.sort((a, b) -> a.getDate().compareTo(b.getDate()));
        return reservationsSplitByDays;
    }

    public void saveReservation() {
//        reservationDAO.saveReservation(new Reservation());
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
