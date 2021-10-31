package ru.ravel.bookingCalendar.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ravel.bookingCalendar.DAO.ReservationDAO;
import ru.ravel.bookingCalendar.Models.Client;
import ru.ravel.bookingCalendar.Models.Reservation;
import ru.ravel.bookingCalendar.Models.ReserveCabinet;
import ru.ravel.bookingCalendar.Models.ReserveDay;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationServices {

    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    CabinetService cabinetService;

    public List<ReserveDay> getActualReservations() throws ParseException {
        List<Reservation> actualReservations = reservationDAO.getActualReservations();
        List<ReserveDay> reservationsSplitByDays = new ArrayList<>();
        {
            DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            Map<String, List<Reservation>> reservationsSplitByDaysMap = actualReservations.stream().collect(
                    Collectors.groupingBy(w -> dateFormatter.format(w.getStartTime()))
            );
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
        }
        reservationsSplitByDays.sort((a, b) -> a.getDate().compareTo(b.getDate()));
        return reservationsSplitByDays;
    }

    public void saveReservation(String reservationString, Client client) {
        String[] lines = reservationString.split("\n");
        Reservation reservation = Reservation.builder()
                .clientName(client.getName())
                .startTime(parseDateTime(lines[0]))
                .duration(Long.parseLong(lines[1]))
                .cabinetId(cabinetService.getCabinetIdByNumber(Long.parseLong(lines[2])))
                .title(lines[3])
                .color(generateRandomColor())
                .build();
        reservationDAO.saveReservation(reservation, client.getId());
    }

    private Date parseDateTime(String dateString) {
        try {
            Date today = new Date();
            today.setMinutes(0);
            today.setHours(0);
            today.setSeconds(0);
            if (dateString.indexOf(':') - dateString.indexOf(' ') == 2) {
                dateString = new StringBuilder (dateString).insert(dateString.indexOf(':')-1, "0").toString();
            }
            if (dateString.indexOf('.') == 1) {
                dateString = new StringBuilder (dateString).insert(dateString.indexOf('.')-1, "0").toString();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.dd.MM HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(today.getYear() + 1900 + "." + dateString, formatter);
            Date date = java.sql.Timestamp.valueOf(dateTime);
            Date tmp = date;
            tmp.setMinutes(0);
            tmp.setHours(0);
            if (tmp.compareTo(today) < 0) {
                date.setYear(today.getYear() + 1);
            }
            TimeZone.setDefault(TimeZone.getTimeZone("UTC+3"));
            date.setHours(dateTime.getHour()+3);
            date.setMinutes(dateTime.getMinute());
            return date;
        } catch (Exception e) {
            throw  e;
        }
    }


    private String generateRandomColor() {
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
        return new StringBuilder().append(red).append(" ").append(green).append(" ").append(blue).toString();
    }

}
