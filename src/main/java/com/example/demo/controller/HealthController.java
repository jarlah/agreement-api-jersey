package com.example.demo.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/health")
public class HealthController {
  @GET
  public String healthCheck() {
    // Maybe verify dependencies with @Inject and possibly run a light call?
    return "OK";
  }
}
