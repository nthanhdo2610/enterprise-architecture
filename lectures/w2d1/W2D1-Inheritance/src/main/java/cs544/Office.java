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

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long room_number;
    private String building;
    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Employee> employees;

    Office(String building) {
        this.building = building;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employee.setOffice(this);
        this.employees.add(employee);
    }
}
