package cs544.token;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;

public class TokenServer {
    public TokenServer() {
    }
    

    public boolean verifyToken(String token) {
        ResponseEntity<String> response = connectToTokenServer(token, "verify");
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Token verification successful");
            return true;
        } else {
            System.out.println("Token verification failed");
            return false;
        }
    }

    public String verifyTokenRole(String token) {
        ResponseEntity<String> response = connectToTokenServer(token, "role");
        if (response.getStatusCode().is2xxSuccessful()) {
            // System.out.println("====="+response);
            if (response.getBody().equals("User is Admin")){
                // System.out.println("verifyTokenRole Token verification successful");
                return "ROLE_ADMIN";
            }else{
                return "ROLE_USER";
            }
        } else {
            System.out.println("verifyTokenRole Token verification failed");
            return "ERROR TOKEN";
        }
    }

    @Value("${services.auth.host}")
    private String authHost;
    @Value("${services.auth.port}")
    private int authPort;
    @Value("${services.auth.context-path}")
    private String authContextPath;
    public ResponseEntity<String> connectToTokenServer(String token, String type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        String auth_url = "http://"+authHost+":"+authPort+authContextPath;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(auth_url)
                .queryParam("token", token);
        URI uri = builder.build().toUri();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        HttpMethod connection_type = HttpMethod.GET;
        if (type.equals("role")){
            connection_type = HttpMethod.PUT;
        }
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                connection_type,
                requestEntity,
                String.class);
        return response;
    }

}
