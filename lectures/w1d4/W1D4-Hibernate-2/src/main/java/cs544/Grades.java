package cs544;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity(name = "cs544.Grades")
@Table(name = "grades")
public class Grades {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "student_id", nullable = false)
    private Integer studentId;
    @Column(name = "course_id", nullable = false)
    private Integer courseId;
    @Column(name = "grade")
    private String grade;
}