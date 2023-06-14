package cs544;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarDao extends JpaRepository<Car, Integer> {

}