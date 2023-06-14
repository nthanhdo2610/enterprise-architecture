package cs544.dao;

import cs544.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Car, Integer> {

}