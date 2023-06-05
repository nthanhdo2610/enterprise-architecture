package cs544;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "School_id")
    @MapKey(name = "id")
    private Map<String, Student> students;

    School(String name, Map<String, Student> students) {
        if (students.isEmpty()) {
            throw new RuntimeException("Invalid School!");
        }
        this.name = name;
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.put(student.getId(), student);
    }
}
