package cs544.cov1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Email {
	@Id
	@GeneratedValue
	private long id;
	@NotEmpty
	@jakarta.validation.constraints.Email
	private String email;

	public Email() {
		super();
	}

	public Email(String email) {
		super();
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
