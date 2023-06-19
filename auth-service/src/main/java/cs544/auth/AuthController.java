package cs544.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/testing")
    public String getAll() {
        return "AuthController";
    }
}
