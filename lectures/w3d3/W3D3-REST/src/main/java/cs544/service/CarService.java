package cs544.service;

import java.util.List;

import cs544.dao.CarDao;
import cs544.domain.Car;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarService {
    private final CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<Car> getAll() {
        return carDao.findAll();
    }

    public void add(Car car) {
        carDao.save(car);
    }

    public Car get(int id) {
        return carDao.getReferenceById(id);
    }

    public void update(Car car) {
        carDao.save(car);
    }

    public void delete(int id) {
        carDao.deleteById(id);
    }
}