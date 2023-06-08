package cs544.bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date date;
	private double amount;
	private String description;
	private String fromAccountNumber;
	private String fromPersonName;

	public AccountEntry(Date date, double amount, String description, String fromAccountNumber, String fromPersonName) {
		super();
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.fromAccountNumber = fromAccountNumber;
		this.fromPersonName = fromPersonName;
	}
	
}
