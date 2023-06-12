package cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

	@Autowired
	private CarService carService;
	
	@GetMapping("/")
	public String redirectRoot() {
		return "redirect:/cars";
	}
	
	@GetMapping("/cars")
	public String getAll(Model model) {
		model.addAttribute("cars", carService.getAll());
		return "carList";
	}
	
	@PostMapping("/cars")
	public String add(Car car) {
		carService.add(car);
		return "redirect:/cars";
	}

	@GetMapping("/cars/add")
	public String viewAdd(@ModelAttribute Car car, Model model) {
		model.addAttribute("msg", "Add");
		return "carDetail";
	}

	@GetMapping("/cars/{id}")
	public String get(@PathVariable int id, Model model) {
		model.addAttribute("car", carService.get(id));
		model.addAttribute("msg", "Update");
		return "carDetail";
	}
	
	@PostMapping("/cars/{id}")
	public String update(Car car, @PathVariable int id) {
		carService.update(car); // car.id already set by binding
		return "redirect:/cars";
	}
	
	@PostMapping(value="/cars/delete")
	public String delete(int carId) {
		carService.delete(carId);
		return "redirect:/cars";
	}
}
