package app.routes;

import app.entities.Person;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {
    private PersonRoute personRoute = new PersonRoute();

    public EndpointGroup getRoutes() {
        return () -> {
            path("/", personRoute.addPersonRoutes());

        };
    }
}