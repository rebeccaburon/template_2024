package app.config;

import app.exceptions.ApiException;
import app.controller.ExceptionController;
import app.routes.Routes;
import app.utils.ApiProps;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;

public class AppConfig {
    private static Routes routes;
    private static final ExceptionController exceptionController = new ExceptionController();



    private static void configuration (JavalinConfig config){
        //Server
        config.router.contextPath = ApiProps.API_CONTEXT;
        config.showJavalinBanner = false;
        config.http.defaultContentType = "application/json"; // default content type for requests

        //Plugin
        config.bundledPlugins.enableRouteOverview("/routes");
        config.bundledPlugins.enableDevLogging();

        config.router.apiBuilder(routes.getRoutes());
        //config.router.apiBuilder(SecurityRoutes.getSecurityRoutes());
    }


    //start server
    public static void startServer() {
        routes = new Routes();
        var app = Javalin.create(AppConfig::configuration);
        //ADD exception
        app.exception(ApiException.class, exceptionController::apiExceptionHandler);
        app.exception(Exception.class, exceptionController::exceptionHandler);
        app.start(ApiProps.PORT);
    }
    //stop server
    public static void stopServer(Javalin app) {
        app.stop();
    }
}
