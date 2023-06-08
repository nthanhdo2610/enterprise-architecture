package cs544;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Student {
	@Id
	private long studentid;
	private String firstname;
	private String lastname;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "studentid")
	@ToString.Exclude
	private Collection<Course> courselist = new ArrayList<>();

	public Student(long studentid, String firstname, String lastname) {
		this.studentid = studentid;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public void addCourse(Course course) {
		this.courselist.add(course);
	}

}
