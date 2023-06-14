package cs544;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Component
public class ClientApp implements CommandLineRunner {

    private static final Faker faker = new Faker(Locale.US);
    private final BookServiceProxy bookServiceProxy;

    public ClientApp(BookServiceProxy bookServiceProxy) {
        this.bookServiceProxy = bookServiceProxy;
    }
    private static final Random rand = new Random();

    @Override
    public void run(String... args) {
        System.out.println(bookServiceProxy.getAll());
        BookDto book = new BookDto(faker.book().title(), faker.code().isbn13(), faker.book().author(), 39);
        bookServiceProxy.add(book);
        book.setPrice(33.00);
        bookServiceProxy.update(book);
        bookServiceProxy.delete(1);
        System.out.println(bookServiceProxy.getAll());
        book = bookServiceProxy.get(2);
    }
}
