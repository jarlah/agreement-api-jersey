package com.example.demo.services.integration;

import com.example.demo.services.business.exceptions.CreateAgreementFailedException;
import com.example.demo.services.business.exceptions.CreateCustomerFailedException;
import com.example.demo.services.business.exceptions.UpdateAgreementStatusFailedException;
import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.integration.models.NewAgreement;
import com.example.demo.services.letter.exceptions.LetterFailedExceptionException;

/** This service might call out to an external api */
public interface IntegrationService {

  Agreement createAgreement(NewAgreement newAgreementDto)
      throws LetterFailedExceptionException, CreateCustomerFailedException,
          CreateAgreementFailedException, UpdateAgreementStatusFailedException;
}
