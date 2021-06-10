package SpringBoot.SMikhail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showMyLoginPage() {
        return "login";
    }

    @GetMapping()
    public String getHomePage() {
        return "login";
    }

    @GetMapping("/main")
    public String getMainPage() {
        return "ListModalUsers";
    }

}
