package cs544;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class App {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs544");
    private static final Faker faker = new Faker(Locale.US);

    public static void main(String[] args) {
//        associationDepartment();
        associationCustomer();
    }

    static void associationCustomer() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Customer customer = new Customer(faker.name().fullName());
        customer.addReservation(new Reservation(new Book(faker.code().isbn13(), faker.book().title(), faker.book().author(), new Publisher(faker.company().name())), faker.date().future(30, TimeUnit.DAYS)));
        customer.addReservation(new Reservation(new Book(faker.code().isbn13(), faker.book().title(), faker.book().author(), new Publisher(faker.company().name())), faker.date().future(30, TimeUnit.DAYS)));
        customer.addReservation(new Reservation(new Book(faker.code().isbn13(), faker.book().title(), faker.book().author(), new Publisher(faker.company().name())), faker.date().future(30, TimeUnit.DAYS)));
        em.persist(customer);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
        query.getResultList().forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }

    static void associationDepartment() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Department department = new Department(faker.commerce().department());
        Office office = new Office(faker.commerce().department());
        Employee employee1 = new Employee(faker.name().fullName(), department, office);
        em.persist(employee1);
        Employee employee2 = new Employee(faker.name().fullName(), department, office);
        em.persist(employee2);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Employee> query = em.createQuery("from Employee", Employee.class);
        query.getResultList().forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

