package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.config.JerseyConfig;
import com.example.demo.services.business.BusinessService;
import com.example.demo.services.business.exceptions.CreateAgreementFailedException;
import com.example.demo.services.business.exceptions.CreateCustomerFailedException;
import com.example.demo.services.business.exceptions.UpdateAgreementStatusFailedException;
import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.business.models.AgreementStatus;
import com.example.demo.services.business.models.Customer;
import com.example.demo.services.integration.IntegrationService;
import com.example.demo.services.integration.IntegrationServiceImpl;
import com.example.demo.services.letter.LetterService;
import com.example.demo.services.letter.exceptions.LetterFailedExceptionException;
import com.example.demo.services.letter.models.LetterStatus;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class IntegrationControllerTest extends JerseyTest {
  private final LetterService mockLetterService = Mockito.mock(LetterService.class);
  private final BusinessService mockBusinessService = Mockito.mock(BusinessService.class);

  @Test
  public void test()
      throws LetterFailedExceptionException, CreateCustomerFailedException,
          CreateAgreementFailedException, UpdateAgreementStatusFailedException {
    // Given:
    var customerId = UUID.randomUUID();
    var customerPid = "1234";
    var customerName = "Test customer";
    var customer = new Customer(customerId, customerPid, customerName);
    var agreementId = UUID.randomUUID();
    var agreementPrice = 1000;
    var agreement =
        new Agreement(
            agreementId, AgreementStatus.DRAFT, BigDecimal.valueOf(agreementPrice), customerId);
    Mockito.when(mockLetterService.sendAgreementLetterToCustomer(agreement, customer))
        .thenReturn(LetterStatus.SENT_OK);
    Mockito.when(mockBusinessService.createCustomer(customerPid, customerName))
        .thenReturn(customer);
    Mockito.when(
            mockBusinessService.createAgreement(customerId, BigDecimal.valueOf(agreementPrice)))
        .thenReturn(agreement);
    Mockito.when(
            mockBusinessService.updateAgreementStatus(agreement, AgreementStatus.AGREEMENT_SENT))
        .thenReturn(
            new Agreement(
                agreementId, AgreementStatus.AGREEMENT_SENT, BigDecimal.valueOf(1000), customerId));

    // When:
    Map<String, Object> data = new HashMap<>();
    data.put("customerPid", customerPid);
    data.put("customerName", customerName);
    data.put("agreementPrice", agreementPrice);
    Response response =
        target("/api/agreement")
            .request()
            .header("Content-Type", "application/json")
            .post(Entity.entity(data, "application/json"));

    // Then:
    assertEquals(200, response.getStatus());
    assertEquals(
        "{\"agreementPrice\":1000,\"customerId\":\"%s\",\"id\":\"%s\",\"status\":\"AGREEMENT_SENT\"}"
            .formatted(customerId, agreementId),
        response.readEntity(String.class));
  }

  @Test
  public void createAgreementWithBadCustomerPid() {
    // Given:
    Map<String, Object> data = new HashMap<>();
    data.put("customerPid", "111111111111111111");
    data.put("customerName", "Donald Duck");
    data.put("agreementPrice", 1000);

    // When:
    Response response =
        target("/api/agreement")
            .request()
            .header("Content-Type", "application/json")
            .post(Entity.entity(data, "application/json"));

    // Then:
    assertEquals(400, response.getStatus());
    assertEquals(
        "{\"errors\":[{\"field\":\"createAgreement.arg0.customerPid\",\"message\":\"size must be between 1 and 11\"}]}",
        response.readEntity(String.class));
  }

  @Test
  public void createAgreementWithBadAgreementPrice() {
    // Given:
    Map<String, Object> data = new HashMap<>();
    data.put("customerPid", "01019912345");
    data.put("customerName", "Donald Duck");
    data.put("agreementPrice", 0);

    // When:
    Response response =
        target("/api/agreement")
            .request()
            .header("Content-Type", "application/json")
            .post(Entity.entity(data, "application/json"));

    // Then:
    assertEquals(400, response.getStatus());
    assertEquals(
        "{\"errors\":[{\"field\":\"createAgreement.arg0.agreementPrice\",\"message\":\"must be greater than or equal to 1\"}]}",
        response.readEntity(String.class));
  }

  @Test
  public void createAgreementWithBadCustomerName() {
    // Given:
    Map<String, Object> data = new HashMap<>();
    data.put("customerPid", "01019912345");
    data.put("customerName", "");
    data.put("agreementPrice", 1000);

    // When:
    Response response =
        target("/api/agreement")
            .request()
            .header("Content-Type", "application/json")
            .post(Entity.entity(data, "application/json"));

    // Then:
    assertEquals(400, response.getStatus());
    assertEquals(
        "{\"errors\":[{\"field\":\"createAgreement.arg0.customerName\",\"message\":\"size must be between 1 and 100\"}]}",
        response.readEntity(String.class));
  }

  @Override
  protected Application configure() {
    return JerseyConfig.getResourceConfig(
        new AbstractBinder() {
          @Override
          protected void configure() {
            bind(mockLetterService).to(LetterService.class);
            bind(mockBusinessService).to(BusinessService.class);
            bind(IntegrationServiceImpl.class).to(IntegrationService.class);
          }
        });
  }
}
