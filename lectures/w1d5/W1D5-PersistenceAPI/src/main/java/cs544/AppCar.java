package cs544;

import java.util.List;

import cs544.domain.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AppCar {

	private static EntityManagerFactory emf;
    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");
        		
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Create new instance of Car and set values in it
        Car car1 = new Car("BMW", "SDA231", 30221.00);
        // save the car
        em.persist(car1);
        // Create new instance of Car and set values in it
        Car car2 = new Car("Mercedes", "HOO100", 4088.00);
        // save the car
        em.persist(car2);
        // Print “Em contains Car1 before clear” if it contains car1 after car2 has been persisted
        if(em.contains(car1)){
            System.out.println("Em contains Car1 before clear");
        }
        // Clear the entity manager cache
        em.clear();
        // Print “Em contains Car1 after clear” if it contains car1 after the clear
        if(em.contains(car1)){
            System.out.println("Em contains Car1 before clear");
        }
        // Change the price of car1 to 50000
        car1.setPrice(50000.00);
        // Merge car1 (but do not store the return value of merge)
        em.merge(car1);
        // Update the code to store the return value of merge into the Car1 variable
        car1 = em.merge(car1);
        // Change the year of car1 to 2022
        car1.setYear("2022");
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retieve all cars
        TypedQuery<Car> query = em.createQuery("from Car", Car.class);
        List<Car> carList = query.getResultList();
        for (Car car : carList) {
            System.out.println("brand= " + car.getBrand() + ", year= "
                    + car.getYear() + ", price= " + car.getPrice());
        }
        em.getTransaction().commit();
        em.close();
    }
}

