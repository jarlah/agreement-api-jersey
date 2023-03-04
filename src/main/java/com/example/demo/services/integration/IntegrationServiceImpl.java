package com.example.demo.services.integration;

import com.example.demo.services.business.BusinessService;
import com.example.demo.services.business.exceptions.CreateAgreementFailedException;
import com.example.demo.services.business.exceptions.CreateCustomerFailedException;
import com.example.demo.services.business.exceptions.UpdateAgreementStatusFailedException;
import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.business.models.AgreementStatus;
import com.example.demo.services.integration.models.NewAgreement;
import com.example.demo.services.letter.LetterService;
import com.example.demo.services.letter.exceptions.LetterFailedExceptionException;
import jakarta.inject.Inject;

public class IntegrationServiceImpl implements IntegrationService {

  private final BusinessService businessService;
  private final LetterService letterService;

  @Inject
  public IntegrationServiceImpl(BusinessService businessService, LetterService letterService) {
    this.businessService = businessService;
    this.letterService = letterService;
  }

  @Override
  public Agreement createAgreement(NewAgreement newAgreement)
      throws CreateCustomerFailedException, CreateAgreementFailedException,
          UpdateAgreementStatusFailedException, LetterFailedExceptionException {
    var customer =
        businessService.createCustomer(newAgreement.customerPid(), newAgreement.customerName());
    var agreement = businessService.createAgreement(customer.id(), newAgreement.agreementPrice());
    // Can atm only be successful
    var status = letterService.sendAgreementLetterToCustomer(agreement, customer);
    return businessService.updateAgreementStatus(agreement, AgreementStatus.AGREEMENT_SENT);
  }
}
