package cs544.token;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class TokenServer {
    public TokenServer() {
    }

    public boolean verifyToken(String token) {
        ResponseEntity<String> response = connectToTokenServer(token);
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Token verification successful");
            return true;
        } else {
            System.out.println("Token verification failed");
            return false;
        }
    }

    public ResponseEntity<String> connectToTokenServer(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create the URI with the token as a query parameter
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8080/auth/")
                .queryParam("token", token);

        // Build the URI
        URI uri = builder.build().toUri();

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Call the external API using GET method
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                requestEntity,
                String.class);

        return response;
    }

}
