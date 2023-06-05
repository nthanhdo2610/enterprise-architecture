package cs544;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Passenger_id")
    private List<Flight> flights;

    Passenger(String name, List<Flight> flights) {
        if (flights.isEmpty()) {
            throw new RuntimeException("Invalid Passenger!");
        }
        this.name = name;
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }
}
