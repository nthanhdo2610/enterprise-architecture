package cs544.quiz;

import jakarta.persistence.Embeddable;

@Embeddable
public class PhoneNumber {
    private String number;

    private String type;
}
