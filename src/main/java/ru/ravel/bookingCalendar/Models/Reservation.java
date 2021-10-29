package ru.ravel.bookingCalendar.Models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Reservation {
    private long id;
    private long user;
    private long cabinetId;
    private Date startTime;
    private long duration;
    private String title;
    private String color;
}
