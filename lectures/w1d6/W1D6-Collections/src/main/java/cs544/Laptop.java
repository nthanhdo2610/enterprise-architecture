package cs544;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private String type;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Employee employee;

    public Laptop(String brand, String type, Employee employee) {
        if (employee == null) {
            throw new RuntimeException("Missing Owner!");
        }
        this.brand = brand;
        this.type = type;
        this.employee = employee;
        employee.addLaptop(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Laptop laptop = (Laptop) o;

        return new EqualsBuilder().append(id, laptop.id).append(brand, laptop.brand).append(type, laptop.type).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(brand).append(type).toHashCode();
    }
}
