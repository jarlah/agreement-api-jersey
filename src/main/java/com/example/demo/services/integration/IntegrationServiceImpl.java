package com.example.demo.services.integration;

import com.example.demo.services.business.BusinessService;
import com.example.demo.services.business.exceptions.CreateAgreementFailedException;
import com.example.demo.services.business.exceptions.CreateCustomerFailedException;
import com.example.demo.services.business.exceptions.UpdateAgreementStatusFailedException;
import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.business.models.AgreementStatus;
import com.example.demo.services.integration.exceptions.SendAgreementLetterFailedException;
import com.example.demo.services.integration.models.NewAgreement;
import com.example.demo.services.letter.LetterService;
import com.example.demo.services.letter.exceptions.LetterFailedExceptionException;
import com.example.demo.services.letter.models.LetterStatus;
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
      throws LetterFailedExceptionException, CreateCustomerFailedException,
          CreateAgreementFailedException, UpdateAgreementStatusFailedException,
          SendAgreementLetterFailedException {
    var customer =
        businessService.createCustomer(newAgreement.customerPid(), newAgreement.customerName());
    var agreement = businessService.createAgreement(customer.id(), newAgreement.agreementPrice());
    var status = letterService.sendAgreementLetterToCustomer(agreement, customer);
    if (status == LetterStatus.SENT_OK) {
      agreement = businessService.updateAgreementStatus(agreement, AgreementStatus.AGREEMENT_SENT);
    } else {
      throw new SendAgreementLetterFailedException("Status for letter is: %s".formatted(status));
    }
    return agreement;
  }
}
