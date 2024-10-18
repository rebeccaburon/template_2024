package app.controller;

import app.config.HibernateConfig;
import app.dao.PersonDAO;
import app.dto.PersonDTO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class PersonController {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("person");
    private PersonDAO personDAO = new PersonDAO(emf);
    private PersonDTO personDTO = new PersonDTO();

    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //Create
    public void create(Context ctx) {
        try{
            personDTO = ctx.bodyAsClass(PersonDTO.class);
            PersonDTO newPerson = personDAO.create (personDTO);
            ctx.status(201);
            ctx.json(newPerson);
        } catch (Exception e){
            ctx.status(500);
            e.printStackTrace();
            ctx.result("Creating person failed" + e.getMessage());
        }
    }
    //GET BY ID
    public void getById(Context ctx){
        long personId = Long.parseLong(ctx.pathParam("id"));
        personDTO = personDAO.getById(personId);

        try{
            if(personDTO != null){
                ctx.status(200);
                ctx.json(personDTO);
            } else {
                ctx.status(404);
                ctx.result("Not found");
            }
        } catch (Exception e) {
            //ADD EXCEPTIONS
            throw new RuntimeException(e);
        }
    }
    //GET ALL
    public void getAll(Context ctx) {
        List<PersonDTO> personDTOList = personDAO.getAll();
        if (personDTOList.isEmpty()) {
            ctx.status(404);
            ctx.result("There are no persons in the list");
        } else {
            ctx.status(200);
            ctx.json(personDTOList);
        }
    }
    //Update
    public void update(Context ctx) {
        long personId = Long.parseLong(ctx.pathParam("id"));
        personDTO = ctx.bodyAsClass(PersonDTO.class);
        personDTO = personDAO.update (personId, personDTO);
        ctx.status(201);
        ctx.json(personDTO);
    }
    //Delete
    public void delete(Context ctx) {
        long personId = Long.parseLong(ctx.pathParam("id"));
        personDAO.delete(personId);
        ctx.status(201);
    }
}
