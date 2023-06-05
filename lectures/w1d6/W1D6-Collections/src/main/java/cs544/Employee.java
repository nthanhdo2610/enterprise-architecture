package cs544;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    @OneToMany(mappedBy = "employee", cascade= CascadeType.PERSIST)
    @ToString.Exclude
    private Set<Laptop> laptops;

    Employee(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.laptops = new HashSet<>();
    }

    public void addLaptop(Laptop laptop){
        this.laptops.add(laptop);
    }

}
