package cs544;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employee_number;
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Department department;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Office office;

    Employee(String name, Department department, Office office) {
        if (department == null || office == null) {
            throw new RuntimeException("Invalid Employee!");
        }
        this.name = name;
        this.department = department;
        this.office = office;
        department.addEmployee(this);
        office.addEmployee(this);
    }

}
