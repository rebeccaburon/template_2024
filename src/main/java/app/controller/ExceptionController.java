package app.controller;

import app.exceptions.ApiException;
import app.exceptions.Message;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ExceptionController {
    private final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    public void apiExceptionHandler(ApiException e, Context ctx) {
        log.error("{} {}", e.getStatusCode(), e.getMessage());
        ctx.status(e.getStatusCode());

        // Format the current LocalDateTime
        String formattedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Create the Message instance
        Message errorResponse = new Message(e.getStatusCode(), e.getMessage());

        // Send the error response with the formatted time as a separate field
        ctx.json(Map.of(
                "error message", errorResponse,
                "time of error", formattedTime
        ));
    }
    public void exceptionHandler(Exception e, Context ctx) {
        log.error("{} {}", 400, e.getMessage());
        ctx.status(400);
        ctx.result(e.getMessage());
    }



}

