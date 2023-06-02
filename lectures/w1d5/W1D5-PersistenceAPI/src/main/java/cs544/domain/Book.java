package cs544.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    private String ISBN;
    private String title;
    private String author;
    private double price;
    private Date publish_date;

    public Book(String title, String ISBN, String author, double price, Date publish_date) {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.price = price;
        this.publish_date = publish_date;
    }
}
