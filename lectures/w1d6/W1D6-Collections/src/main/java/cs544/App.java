package cs544;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs544");
    private static final Faker faker = new Faker(Locale.US);

    public static void main(String[] args) {
        associationEmployeeLaptop();
        associationPassengerFlight();
        associationSchoolStudent();

    }

    static void associationSchoolStudent() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Student student1 = new Student(faker.code().ean8(), faker.name().firstName(), faker.name().lastName());
        Student student2 = new Student(faker.code().ean8(), faker.name().firstName(), faker.name().lastName());
        School school = new School(
                faker.address().cityName(),
                Stream.of(student1, student2)
                .collect(Collectors.toMap(Student::getId, Function.identity())));
        em.persist(school);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<School> query = em.createQuery("from School", School.class);
        query.getResultList().forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }

    static void associationEmployeeLaptop() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Employee employee = new Employee(faker.name().firstName(), faker.name().lastName());

        Laptop laptop1 = new Laptop("Apple", "Macbook", employee);
        em.persist(laptop1);
        Laptop laptop2 = new Laptop("Dell", "FPS", employee);
        em.persist(laptop2);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Laptop> query = em.createQuery("from Laptop", Laptop.class);
        query.getResultList().forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }

    static void associationPassengerFlight() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Flight flight1 = new Flight(faker.idNumber().valid(), faker.address().cityName(), faker.address().cityName(),
                faker.date().future(30, TimeUnit.DAYS));
//        em.persist(flight1);
        Flight flight2 = new Flight(faker.idNumber().valid(), faker.address().cityName(), faker.address().cityName(),
                faker.date().future(30, TimeUnit.DAYS));
//        em.persist(flight2);

        Passenger passenger = new Passenger(faker.name().firstName(), Arrays.asList(flight1, flight2));
        em.persist(passenger);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Passenger> query = em.createQuery("from Passenger", Passenger.class);
        query.getResultList().forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

