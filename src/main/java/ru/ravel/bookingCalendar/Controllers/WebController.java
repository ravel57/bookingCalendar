package ru.ravel.bookingCalendar.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ravel.bookingCalendar.Services.ReservationServices;

import java.text.ParseException;

@Controller
//@RequestMapping("/")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebController {

    @Autowired
    private ReservationServices reservations;

    @GetMapping("/*")
    public String getRootRequest() {
//        httpSession.setAttribute("", "");
        return "Main";
    }

    @GetMapping("/API/v1/reservations")
    public ResponseEntity<Object> getReservationsRequest() throws ParseException {
        return ResponseEntity.status(HttpStatus.OK).body(reservations.getActualReservations());
    }


}
