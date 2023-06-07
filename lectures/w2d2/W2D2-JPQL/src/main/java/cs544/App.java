package cs544;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class App {

	private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");
        questionA("USA", 500);

//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//
//        // b) TODO: All airlines that use A380 airplanes
//        System.out.println("Question B:");
//        List<Airline> airlines = em.createQuery("from Airline ", Airline.class).getResultList();
//        System.out.println("Airlines that use A380s:");
//        for (Airline airline : airlines) {
//            System.out.println(airline.getName());
//        }
//        System.out.println();
//
//        em.getTransaction().commit();
//        em.close();
//
//        em = emf.createEntityManager();
//        em.getTransaction().begin();
//
//        // c) TODO: Flights using 747 planes that don't belong to Star Alliance
//        System.out.println("Question C:");
//        flights = em.createQuery("from Flight ", Flight.class).getResultList();
//        System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:",
//                "Arrives:");
//        for (Flight flight : flights) {
//            System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
//                    flight.getFlightnr(), flight.getOrigin().getCity(),
//                    flight.getDepartureDate(), flight.getDepartureTime(),
//                    flight.getDestination().getCity(),
//                    flight.getArrivalDate(), flight.getArrivalTime());
//        }
//        System.out.println();
//
//        em.getTransaction().commit();
//        em.close();
//
//        em = emf.createEntityManager();
//        em.getTransaction().begin();
//
//        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
//                Locale.US);
//        DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,
//                Locale.US);
//
//        // d) TODO: All flights leaving before 12pm on 08/07/2009
//        System.out.println("Question D:");
//        TypedQuery<Flight> query = em.createQuery("from Flight ", Flight.class);
//        flights = query.getResultList();
//        System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:",
//                "Arrives:");
//        for (Flight flight : flights) {
//            System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
//                    flight.getFlightnr(), flight.getOrigin().getCity(),
//                    flight.getDepartureDate(), flight.getDepartureTime(),
//                    flight.getDestination().getCity(),
//                    flight.getArrivalDate(), flight.getArrivalTime());
//        }
//        System.out.println();
//        em.getTransaction().commit();
//        em.close();
    }

    static void questionA(String country, int cap){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // a) TODO: Flights leaving USA capacity > 500
        /**
         * select f.* from Flight f
         *     inner join Airport a on f.origin_id = a.id and a.country = 'USA'
         *     inner join Airplane p on f.airplane_id = p.id and p.capacity > 500;
         */
        System.out.println("Question A:");
        Query query = em.createNativeQuery("select f.* from Flight f " +
                "inner join Airport a on f.origin_id = a.id and a.country = :country " +
                "inner join Airplane p on f.airplane_id = p.id and p.capacity > :cap");
        query.setParameter("country", country);
        query.setParameter("cap", cap);
        List<Object[]> flights = query.getResultList();
        System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:",
                "Arrives:");
//        for (FlightDTO flight : flights) {
//            System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
//                    flight.getFlightnr(),
//                    flight.getOrigin().getCity(),
//                    flight.getDepartureDate(),
//                    flight.getDepartureTime(),
//                    flight.getDestination().getCity(),
//                    flight.getArrivalDate(),
//                    flight.getArrivalTime());
//        }
        System.out.println();

        em.getTransaction().commit();
        em.close();
    }
}
