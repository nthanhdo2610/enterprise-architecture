package cs544;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book extends Product {
    private String title;

    public Book(String name, String description, String title) {
        super(name, description);
        this.title = title;
    }
}
