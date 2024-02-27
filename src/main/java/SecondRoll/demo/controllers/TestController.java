package SecondRoll.demo.controllers;

import SecondRoll.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    UserRepository userRepository;

    // Public route = öppen för alla även ej registrerade
    @GetMapping("/public")
    public String allAccess() {
        return "Public access";
    }


    // User route = måste vara minst User role och inloggad
    @GetMapping("/user")
    public String userAccess() {
        return "User access";
    }

    // Admin route = måste vara Admin role
    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin access";
    }
}