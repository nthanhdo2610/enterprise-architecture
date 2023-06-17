package cs544;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping
    public String getAll() {
        return "MemberController";
    }
}
