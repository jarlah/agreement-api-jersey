package com.example.demo.controller;

import com.example.demo.models.NewAgreementDto;
import com.example.demo.services.business.exceptions.CreateAgreementFailed;
import com.example.demo.services.business.exceptions.CreateCustomerFailed;
import com.example.demo.services.business.exceptions.UpdateAgreementStatusFailed;
import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.integration.IntegrationService;
import com.example.demo.services.integration.exceptions.SendAgreementLetterFailed;
import com.example.demo.services.letter.exceptions.LetterFailedException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class IntegrationController {
//  private final Logger logger = LoggerFactory.getLogger(IntegrationController.class);

  private final IntegrationService integrationService;

  @Inject
  public IntegrationController(IntegrationService integrationService) {
    this.integrationService = integrationService;
  }

  @GET
  @Path("/health")
  public String hello() {
    // Maybe verify dependencies
    return "OK";
  }

  @POST
  @Path("/agreement")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createAgreement(@Valid NewAgreementDto newAgreementDto) {
    Agreement agreement = null;
    try {
      agreement = this.integrationService.createAgreement(newAgreementDto.toServiceModel());
      //logger.info("Successfully created agreement with id [%s]".formatted(agreement.id()));
      return Response.ok(agreement).build();
    } catch (SendAgreementLetterFailed
        | LetterFailedException
        | CreateCustomerFailed
        | CreateAgreementFailed
        | UpdateAgreementStatusFailed e) {
      //logger.error("Failed to create agreement", e);
      return Response.serverError().build();
    }
  }
}
