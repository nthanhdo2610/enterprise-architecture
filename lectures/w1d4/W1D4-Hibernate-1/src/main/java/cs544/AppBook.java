package cs544;

import cs544.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AppBook {

    private static final String FETCH_ALL_BOOK_SQL = "FROM Book";
    private static EntityManagerFactory emf;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

    public static void main(String[] args) throws ParseException {
        emf = Persistence.createEntityManagerFactory("cs544");
        EntityManager em = emf.createEntityManager();

        // Insert data
        em.getTransaction().begin();
        em.persist(new Book(
                "Cracking the Coding Interview: 189 Programming Questions and Solutions",
                "978-0984782857", "Gayle Laakmann McDowell",
                32.82, sdf.parse("07-01-2015")));
        em.persist(new Book("System Design Interview – An insider's guide",
                "979-8664653403", "Alex Xu",
                34.00, sdf.parse("06-12-2020")));
        em.persist(new Book("Designing Data-Intensive Applications: The Big Ideas Behind Reliable, Scalable, and Maintainable Systems",
                "978-1449373320", "Martin Kleppmann",
                30.00, sdf.parse("05-02-2017")));
        em.getTransaction().commit();
        em.close();

        // Select data
        em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Book> query = em.createQuery(FETCH_ALL_BOOK_SQL, Book.class);
        List<Book> ls = query.getResultList();
        ls.forEach(Book::toString);
        em.getTransaction().commit();
        em.close();

        // Update data
        em = emf.createEntityManager();
        em.getTransaction().begin();
        query = em.createQuery(FETCH_ALL_BOOK_SQL, Book.class);
        ls = query.getResultList();
        if (!ls.isEmpty()) {
            Book b = ls.get(0);
            b.setTitle(b.getTitle() + " updated");
            em.persist(b);
            for (Book book : ls) {
                if (!book.getISBN().equals(b.getISBN())) {
                    em.remove(book);
                }
            }
        }
        em.getTransaction().commit();
        em.close();

        // Select data
        em = emf.createEntityManager();
        em.getTransaction().begin();
        query = em.createQuery(FETCH_ALL_BOOK_SQL, Book.class);
        ls = query.getResultList();
        ls.forEach(Book::toString);
        em.getTransaction().commit();
        em.close();
    }
}
