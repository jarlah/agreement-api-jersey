package com.example.demo;

import com.example.demo.config.JerseyConfig;
import com.example.demo.services.business.BusinessService;
import com.example.demo.services.business.BusinessServiceDummyImpl;
import com.example.demo.services.integration.IntegrationService;
import com.example.demo.services.integration.IntegrationServiceImpl;
import com.example.demo.services.letter.LetterService;
import com.example.demo.services.letter.LetterServiceDummyImpl;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

/** Main class. */
public class Main {
  public static final String BASE_URI = "http://localhost:8080/";

  public static HttpServer startServer() {
    var rc =
        JerseyConfig.getResourceConfig(
            new AbstractBinder() {
              @Override
              protected void configure() {
                bind(LetterServiceDummyImpl.class).to(LetterService.class);
                bind(IntegrationServiceImpl.class).to(IntegrationService.class);
                bind(BusinessServiceDummyImpl.class).to(BusinessService.class);
              }
            });
    return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
  }

  public static void main(String[] args) {
    startServer();
    System.out.printf("Jersey app started with endpoints available at %s%n", BASE_URI);
  }
}
