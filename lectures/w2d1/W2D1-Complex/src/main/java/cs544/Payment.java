package cs544;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Payment {
    @JoinColumn(nullable = false)
    private String pay_date;
    @JoinColumn(nullable = false)
    private double amount;
}
