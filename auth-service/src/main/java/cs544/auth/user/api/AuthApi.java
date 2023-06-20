package cs544.auth.user.api;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cs544.auth.jwt.JwtTokenUtil;
import cs544.auth.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
public class AuthApi {
	@Autowired AuthenticationManager authManager;
	@Autowired JwtTokenUtil jwtUtil;

	
	@Tag(name = "validateIsAdmin", description = "To check if user provided token is admin user")
	@PutMapping("/")
	public String validateIsAdmin(@RequestParam String token) {
		return jwtUtil.isAdmin(token) ? "User is Admin" : "User is not Admin";
	}


	@Tag(name = "ValidateToken", description = "To check if access token is valid")
	@GetMapping("/")
	public ResponseEntity<?> login(@RequestParam String token) {
		return jwtUtil.validateAccessToken(token) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}
	
	@Tag(name = "Login", description = "To check if login credentials are valid and it will return access token.")
	@PostMapping("/")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			System.out.println(request);
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
			);
			
			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
