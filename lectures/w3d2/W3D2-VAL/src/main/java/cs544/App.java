package cs544;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class App {
    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Car car = new Car();
        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);
        // assertEquals(1, constraintViolations.size());
        // assertEquals("may not be null",
        //         constraintViolations.iterator().next().getMessage());
    }
}