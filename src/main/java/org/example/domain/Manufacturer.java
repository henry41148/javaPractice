package org.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.example.domain.Phone;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="manufacturer")
@Setter
@Getter
@NoArgsConstructor
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private int id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "location", nullable = false, length = 128)
    private String location;

    @Column(name="employee")
    private int employee;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    private List<Phone> phoneList;

    public Manufacturer(int id, String name, String location, int employee) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.employee = employee;
    }

    public int getEmployee() {
        return employee;
    }

    public String toString() {
        return "\nid = " + id + ", name = " + name +  ", location = " + location + ", employeeNumber = " + employee;
    }
}
