package cs544;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(nullable = false)
    private String doctor_type;
    @JoinColumn(nullable = false)
    private String firstname;
    @JoinColumn(nullable = false)
    private String lastname;

    public Doctor(String doctor_type, String firstname, String lastname) {
        this.doctor_type = doctor_type;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
