package com.example.demo.config;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
  private final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

  @Override
  public Response toResponse(Exception exception) {
    if (exception instanceof NotFoundException) {
      return Response.status(404, exception.getMessage()).build();
    }
    // TODO check type of exception and possibly return different responses
    logger.error("Internal server error", exception);
    return Response.serverError().build();
  }
}
