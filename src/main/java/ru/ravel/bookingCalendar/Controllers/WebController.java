package ru.ravel.bookingCalendar.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebController {

    @GetMapping("/*")
    public String getRootRequest(HttpSession httpSession) {
//        httpSession.setAttribute("", "");
        return "Main";
    }

}
