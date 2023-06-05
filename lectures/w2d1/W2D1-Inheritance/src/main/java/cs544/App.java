package cs544;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class App {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs544");
    private static final Faker faker = new Faker(Locale.US);

    public static void main(String[] args) {
        associationCustomer();
    }

    static void associationCustomer() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Customer customer = new Customer(faker.name().firstName(), faker.name().lastName());
        em.persist(customer);

        CD cd = new CD(faker.commerce().productName(), faker.commerce().material(), faker.artist().name());
        em.persist(cd);
        DVD dvd = new DVD(faker.commerce().productName(), faker.commerce().material(), faker.music().genre());
        em.persist(dvd);
        Book book = new Book(faker.commerce().productName(), faker.commerce().material(), faker.book().title());
        em.persist(book);

        List<OrderLine> productCart = new ArrayList<>();
        productCart.add(new OrderLine(cd, 1));
        productCart.add(new OrderLine(dvd, 2));
        productCart.add(new OrderLine(book, 1));

        Order order = customer.addOrder(productCart);
        em.persist(order);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Order> query = em.createQuery("from Order", Order.class);
        query.getResultList().forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

