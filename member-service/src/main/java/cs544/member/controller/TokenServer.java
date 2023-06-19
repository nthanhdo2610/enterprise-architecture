package cs544.member.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
    @Value("${services.auth.host}")
    private String authHost;
    @Value("${services.auth.port}")
    private int authPort;
    @Value("${services.auth.context-path}")
    private String authContextPath;
    public ResponseEntity<String> connectToTokenServer(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        String auth_url = "http://"+authHost+":"+authPort+authContextPath;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(auth_url)
                .queryParam("token", token);
        URI uri = builder.build().toUri();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                requestEntity,
                String.class);
        return response;
    }

}
