package cs544.auth.user.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthRequest {
	@NotNull @Email @Length(min = 3, max = 50)
	private String email;
	
	@NotNull @Length(min = 3, max = 10)
	private String password;
}
