package cs544;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SportController {

    @GetMapping
    public String getAll() {
        return "SportController";
    }
}
