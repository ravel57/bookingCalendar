package ru.ravel.bookingCalendar.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebController {

    @GetMapping("/*")
    public String getRootRequest() {
//        httpSession.setAttribute("", "");
        return "Main";
    }

}
