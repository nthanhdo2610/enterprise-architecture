package cs544.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String title;
    private String ISBN;
    private String author;
    private double price;

    public Book() {
	}

	public Book(String title, String ISBN, String author, double price) {
		super();
		this.title = title;
		this.ISBN = ISBN;
		this.author = author;
        this.price = price;
	}
    
}
