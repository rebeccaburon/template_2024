package app.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "")
public class AEntitie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


}
