package ru.ravel.bookingCalendar.Models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private long id;
    private long telegramId;
    private String name;

}
