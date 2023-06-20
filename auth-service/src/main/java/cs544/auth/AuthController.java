package cs544.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("test")
    public String getAll() {
        return "AuthController";
    }
}
