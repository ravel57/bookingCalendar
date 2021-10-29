package ru.ravel.bookingCalendar.Models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
public class ReserveDay {
    Date date;
    List<ReserveCabinet> cabinets;
}
