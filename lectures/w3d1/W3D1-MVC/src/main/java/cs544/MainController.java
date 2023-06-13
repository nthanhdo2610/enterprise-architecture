package cs544;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String redirectRoot() {
		return "redirect:/books";
	}
}
