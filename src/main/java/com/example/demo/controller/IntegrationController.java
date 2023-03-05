package com.example.demo.controller;

import com.example.demo.config.ValidationErrors;
import com.example.demo.models.NewAgreementDto;
import com.example.demo.services.business.exceptions.CreateAgreementFailedException;
import com.example.demo.services.business.exceptions.CreateCustomerFailedException;
import com.example.demo.services.business.exceptions.UpdateAgreementStatusFailedException;
import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.integration.IntegrationService;
import com.example.demo.services.letter.exceptions.LetterFailedExceptionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api")
@Tag(name = "Integration controller")
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
  @Operation(summary = "New agreement")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "500", description = "Internal server error"),
        @ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = @Content(schema = @Schema(implementation = ValidationErrors.class))),
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(schema = @Schema(implementation = Agreement.class)))
      })
  public Response createNewAgreement(@Valid NewAgreementDto newAgreementDto)
      throws LetterFailedExceptionException, CreateAgreementFailedException,
          CreateCustomerFailedException, UpdateAgreementStatusFailedException {
    var agreement = this.integrationService.createAgreement(newAgreementDto.toServiceModel());
    logger.info("Successfully created agreement with id [%s]".formatted(agreement.id()));
    return Response.ok(agreement).build();
  }
}
