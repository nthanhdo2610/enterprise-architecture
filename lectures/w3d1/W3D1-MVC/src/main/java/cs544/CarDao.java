package cs544;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class CarDao implements ICarDao {
	@PersistenceContext
	private EntityManager em;


	public CarDao() {
		// add(new Car("Volvo", "S80", 1999, "Silver"));
		// add(new Car("Honda", "Accord", 1997, "Red"));
	}
	
	@Override
	public List<Car> getAll() {
		return em.createQuery("from Car", Car.class).getResultList();
	}
	
	@Override
	public void add(Car car) {
		em.persist(car);
	}
	
	@Override
	public Car get(int id) {
		return em.find(Car.class, id);
	}
	
	@Override
	public void update(Car car) {
		em.merge(car);
	}
	
	@Override
	public void delete(int carId) {
		Car c = em.getReference(Car.class, carId);
		em.remove(c);
	}
}
