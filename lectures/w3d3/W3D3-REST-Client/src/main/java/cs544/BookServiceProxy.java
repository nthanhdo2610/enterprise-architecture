package cs544;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BookServiceProxy {
    private final RestTemplate restTemplate;

    private final String bookUrl = "http://localhost:8080/books";
    private final String bookDetailUrl = "http://localhost:8080/books/{id}";

    public BookServiceProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BookDto> getAll() {
        ResponseEntity<List<BookDto>> response = restTemplate.exchange(
                bookUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    public void add(BookDto book) {
        URI uri = restTemplate.postForLocation(bookUrl, book);
        if (uri == null) {
            return;
        }
        Matcher m = Pattern.compile(".*/books/(\\d+)").matcher(uri.getPath());
        m.matches();
        book.setId(Integer.parseInt(m.group(1)));
    }

    public BookDto get(int id) {
        return restTemplate.getForObject(bookDetailUrl, BookDto.class, id);
    }

    public void update(BookDto book) {
        restTemplate.put(bookUrl, book);
    }

    public void delete(int id) {
        restTemplate.delete(bookDetailUrl, id);
    }
}