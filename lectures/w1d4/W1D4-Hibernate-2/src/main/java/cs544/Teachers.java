package cs544;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity(name = "cs544.Teachers")
@Table(name = "teachers")
public class Teachers {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
}