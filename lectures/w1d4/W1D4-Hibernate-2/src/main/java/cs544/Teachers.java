package cs544;

import java.sql.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "cs544.Teachers")
@Table(name = "teachers")
public class Teachers {

  @Id
  @Column(name = "\"id\"", nullable = false)
  private Integer id;
  @Column(name = "\"name\"", nullable = true)
  private String name;
}