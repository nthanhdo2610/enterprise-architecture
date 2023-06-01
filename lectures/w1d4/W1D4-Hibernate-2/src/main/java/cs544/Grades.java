package cs544;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "cs544.Grades")
@Table(name = "grades")
public class Grades {

  @Id
  @Column(name = "\"id\"", nullable = false)
  private Integer id;
  @Column(name = "\"student_id\"", nullable = false)
  private Integer studentId;
  @Column(name = "\"course_id\"", nullable = false)
  private Integer courseId;
  @Column(name = "\"grade\"", nullable = true)
  private String grade;
}