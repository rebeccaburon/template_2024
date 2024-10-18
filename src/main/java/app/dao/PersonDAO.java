package app.dao;

import app.dto.PersonDTO;
import app.entities.Person;
import app.exceptions.JPAException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PersonDAO {
    private EntityManagerFactory emf;

    public PersonDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    //CREAT
    public PersonDTO create(PersonDTO person) {
        Person newperson = new Person();
        newperson.setName(person.getName());
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(newperson);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new JPAException(" Person could not be created " + e.getMessage());
        }
        return new PersonDTO(newperson);
    }

    //GET BY ID
    public PersonDTO getById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            Person person = em.find(Person.class, id);
            if (person != null) {
                return new PersonDTO(person);
            }
        } catch (Exception e) {
            throw new JPAException("ERROR, ID not found" + e.getMessage());
        }
        return null;
    }

    //GET ALL
    public List<PersonDTO> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            return PersonDTO.toDTOList(query.getResultList());
        } catch (Exception e) {
            throw new JPAException("ERROR, no persons found" + e.getMessage());
        }
    }

    //UPDATE
    public PersonDTO update(Long id, PersonDTO personDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);
            person.setName(personDTO.getName());
            Person updatedPerson = em.merge(person);
            em.getTransaction().commit();
            return new PersonDTO(updatedPerson);
        } catch (Exception e) {
            throw new JPAException("ERROR, ID not found. Update not succesfull" + e.getMessage());
        }
    }
    //DELETE

    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);
            if (person != null) {
                em.remove(person);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new JPAException("ERROR, ID"+ id + " not found. Delete not succesfull" + e.getMessage());
        }
    }


}
