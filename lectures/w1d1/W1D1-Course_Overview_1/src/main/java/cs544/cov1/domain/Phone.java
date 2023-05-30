package cs544.cov1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
public class Phone {
	@Id
	@GeneratedValue
	private long id;
        @NotEmpty
        @Pattern(regexp = "[0-9 ()+-]+")
	private String phone;

	public Phone() {
		super();
	}

	public Phone(String phone) {
		super();
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
