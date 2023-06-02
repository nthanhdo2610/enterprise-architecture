package cs544;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getSimpleName());

    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("simpsons");
        getStudents();
        insertStudents(new Students(544, "Edgar Nguyen", "edgar.nguyen@gmail.com", "12345"));
        getStudents();
    }

    public static void getStudents() {
        // Select data
        EntityManager em = emf.createEntityManager();
        logger.info("=====Select data");
        em.getTransaction().begin();
        TypedQuery<Students> query = em.createQuery("from cs544.Students", Students.class);
        List<Students> ls = query.getResultList();
        ls.forEach(s -> logger.info(s.getName()));
        em.getTransaction().commit();
        em.close();
    }

    public static void insertStudents(Students st) {
        // Insert data
        EntityManager em = emf.createEntityManager();
        logger.info("=====Insert data");
        em.getTransaction().begin();
        em.persist(st);
        em.getTransaction().commit();
        em.close();
    }
}
