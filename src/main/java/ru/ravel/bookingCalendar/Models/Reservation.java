package ru.ravel.bookingCalendar.Models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private long id;
    private long userId;
    private long cabinetId;
    private Date startTime;
    private long duration;
    private String title;

}
