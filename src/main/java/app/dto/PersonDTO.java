package app.dto;

import app.entities.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PersonDTO {
    private long id;
    @JsonProperty("name")
    private String name;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
    }


    public static List<PersonDTO> toDTOList(List<Person> persons){
        return persons.stream().map(PersonDTO::new).toList();
    }
}
