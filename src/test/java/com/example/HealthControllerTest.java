package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.Main;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HealthControllerTest {

  private HttpServer server;
  private WebTarget target;

  @BeforeEach
  public void setUp() {
    server = Main.startServer();
    Client c = ClientBuilder.newClient();
    target = c.target(Main.BASE_URI);
  }

  @AfterEach
  public void tearDown() {
    server.shutdownNow();
  }

  /** Test to see that the message "Got it!" is sent in the response. */
  @Test
  public void testGetIt() {
    String responseMsg = target.path("/api/health").request().get(String.class);
    assertEquals("OK", responseMsg);
  }
}
