package com.example.demo.services.business;

import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.business.models.AgreementStatus;
import com.example.demo.services.business.models.Customer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class BusinessServiceDummyImpl implements BusinessService {
  @Override
  public Customer createCustomer(String customerPid, String name) {
    return new Customer(UUID.randomUUID(), customerPid, name);
  }

  @Override
  public Agreement createAgreement(
      UUID customerId, BigDecimal agreementPrice, LocalDate agreementDate) {
    return new Agreement(
        UUID.randomUUID(), AgreementStatus.DRAFT, agreementPrice, agreementDate, customerId);
  }

  @Override
  public Agreement updateAgreementStatus(Agreement agreement, AgreementStatus agreementStatus) {
    return new Agreement(
        agreement.id(),
        agreementStatus,
        agreement.agreementPrice(),
        agreement.agreementDate(),
        agreement.customerId());
  }
}
