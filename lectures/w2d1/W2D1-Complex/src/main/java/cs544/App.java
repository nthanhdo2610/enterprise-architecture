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
        associationAppointment();
    }

    static void associationAppointment() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Doctor doctor = new Doctor(faker.name().prefix(), faker.name().firstName(), faker.name().lastName());
        em.persist(doctor);
        Patient patient = new Patient(faker.name().fullName(), faker.address().streetAddress(), faker.address().zipCode(), faker.address().city());
        em.persist(patient);
        Appointment appointment = new Appointment(faker.date().future(30, TimeUnit.DAYS).toString(), patient, new Payment(faker.date().past(1, TimeUnit.DAYS).toString(), 450.00), doctor);
        em.persist(appointment);
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Appointment> query = em.createQuery("from Appointment", Appointment.class);
        query.getResultList().forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

