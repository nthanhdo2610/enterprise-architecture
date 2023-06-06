package cs544;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SecondaryTables(
        @SecondaryTable(name="Owners", pkJoinColumns= {
                @PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
        })
)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(table="Owners")
    private String owner;
    private String make;
    private String model;
    private int year;
    private String color;
    @OneToMany(mappedBy = "vehicle")
    private List<Wheel> wheels;
}
