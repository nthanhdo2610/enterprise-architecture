package cs544;

import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;
import cs544.domain.Car;
import cs544.domain.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AppCar {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs544");;
    private static final Faker faker = new Faker(Locale.US);

    public static void main(String[] args) throws Exception {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Owner owner = new Owner(faker.name().fullName(), faker.address().fullAddress());

        // Create new instance of Car and set values in it
        Car car1 = new Car("BMW", "SDA231", 30221.00, owner);
        // save the car
        em.persist(car1);
        // Create new instance of Car and set values in it
        Car car2 = new Car("Mercedes", "HOO100", 4088.00, owner);
        // save the car
        em.persist(car2);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retrieve all cars
        TypedQuery<Car> query = em.createQuery("from Car", Car.class);
        List<Car> carList = query.getResultList();
        for (Car car : carList) {
            System.out.println(car);
        }
        em.getTransaction().commit();
        em.close();
    }
}

