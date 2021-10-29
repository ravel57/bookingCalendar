package ru.ravel.bookingCalendar.Models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class ReserveCabinet {
    long cabinetId;
    List<Reservation> reserved;
}
