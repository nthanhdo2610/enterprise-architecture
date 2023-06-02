package cs544;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity(name = "cs544.Courses")
@Table(name = "courses")
public class Courses {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "teacher_id", nullable = false)
    private Integer teacherId;
}