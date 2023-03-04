package com.example.demo.controller;

import com.example.demo.models.NewAgreementDto;
import com.example.demo.services.business.exceptions.CreateAgreementFailedException;
import com.example.demo.services.business.exceptions.CreateCustomerFailedException;
import com.example.demo.services.business.exceptions.UpdateAgreementStatusFailedException;
import com.example.demo.services.integration.IntegrationService;
import com.example.demo.services.letter.exceptions.LetterFailedExceptionException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api")
public class IntegrationController {
  private final Logger logger = LoggerFactory.getLogger(IntegrationController.class);

  private final IntegrationService integrationService;

  @Inject
  public IntegrationController(IntegrationService integrationService) {
    this.integrationService = integrationService;
  }

  @POST
  @Path("/agreement")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createAgreement(@Valid NewAgreementDto newAgreementDto)
      throws LetterFailedExceptionException, CreateAgreementFailedException,
          CreateCustomerFailedException, UpdateAgreementStatusFailedException {
    var agreement = this.integrationService.createAgreement(newAgreementDto.toServiceModel());
    logger.info("Successfully created agreement with id [%s]".formatted(agreement.id()));
    return Response.ok(agreement).build();
  }
}
