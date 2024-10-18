package app.routes;

import app.config.HibernateConfig;
import app.controller.PersonController;
import app.dao.PersonDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class PersonRoute {
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("persons");
    private PersonDAO personDAO = new PersonDAO(emf);
    private PersonController personController = new PersonController(personDAO);


    protected EndpointGroup addPersonRoutes() {
        return ()->{
            //GET by Id
            get("/person/{id}", personController::getById);

            get("/persons", personController::getAll);

            post("/person", personController::create);

            put("/person/{id}", personController::update);

            delete("/person/{id}", personController::delete);



        };

    }
}