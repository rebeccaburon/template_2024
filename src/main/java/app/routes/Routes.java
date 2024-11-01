package app.routes;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;


public class Routes {

    public Routes(EntityManagerFactory emf) {

    }

    public EndpointGroup getRoutes() {
        return () -> {

        };
    }
}