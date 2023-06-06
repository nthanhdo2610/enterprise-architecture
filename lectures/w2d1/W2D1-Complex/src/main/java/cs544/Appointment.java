package cs544;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(nullable = false)
    private String app_date;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient", nullable = false)
    private Patient patient;
    @Embedded
    private Payment payment;
    @OneToOne
    @JoinColumn(name = "doctor", nullable = false)
    private Doctor doctor;

    public Appointment(String app_date, Patient patient, Payment payment, Doctor doctor) {
        if (doctor == null || payment == null || patient == null) {
            throw new RuntimeException("Invalid Appointment!");
        }
        this.app_date = app_date;
        this.patient = patient;
        this.payment = payment;
        this.doctor = doctor;
    }
}
