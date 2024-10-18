package app.entities;

import app.dto.PersonDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="name", nullable = false)
    private String name;


    public Person(PersonDTO personDTO) {
        this.name = personDTO.getName();
    }
}
