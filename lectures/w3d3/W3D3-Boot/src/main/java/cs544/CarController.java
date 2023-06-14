package cs544;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String redirectRoot() {
        return "redirect:/cars";
    }

    @GetMapping("/cars/addCar")
    public String addCar(@ModelAttribute("car") Car car) {
        return "addCar";
    }

    @PostMapping("/cars/addCar")
    public String add(@Valid Car car, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            // attr.addFlashAttribute("org.springframework.validation.BindingResult.car", result);
            // attr.addFlashAttribute("car", car);
            return "addCar";
        } else {
            carService.add(car);
            return "redirect:/cars";
        }
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

    @GetMapping("/cars/update/{id}")
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("car", carService.get(id));
        model.addAttribute("msg", "Update");
        return "carDetail";
    }

    @PostMapping("/cars/update/{id}")
    public String update(@PathVariable("id") int id, @Valid Car car, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.car", result);
            attr.addFlashAttribute("car", car);
            return "carDetail";
        } else {
            // Update book in the database or perform other operations
            carService.update(car);
            return "redirect:/cars";
        }
    }

    // @PostMapping("/cars/{id}")
    // public String update(Car car, @PathVariable int id) {
    // 	carService.update(car); // car.id already set by binding
    // 	return "redirect:/cars";
    // }

    @PostMapping("/cars/delete/{id}")
    public String delete(@PathVariable("id") int id, @Valid Car car, BindingResult result, RedirectAttributes attr) {

        // Update book in the database or perform other operations
        //   carService.delete(id);
        return "redirect:cars";

    }

    // @PostMapping(value="/cars/delete")
    // public String delete(int carId) {
    // 	carService.delete(carId);
    // 	return "redirect:/cars";
    // }
}
