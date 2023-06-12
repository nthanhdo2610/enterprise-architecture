package cs544;

import java.util.List;

public interface ICarDao {

	public abstract List<Car> getAll();

	public abstract void add(Car car);

	public abstract Car get(int id);

	public abstract void update(Car car);

	public abstract void delete(int carId);

}