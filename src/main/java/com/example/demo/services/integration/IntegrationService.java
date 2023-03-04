package com.example.demo.services.integration;

import com.example.demo.services.business.exceptions.CreateAgreementFailedException;
import com.example.demo.services.business.exceptions.CreateCustomerFailedException;
import com.example.demo.services.business.exceptions.UpdateAgreementStatusFailedException;
import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.integration.exceptions.SendAgreementLetterFailedException;
import com.example.demo.services.integration.models.NewAgreement;
import com.example.demo.services.letter.exceptions.LetterFailedExceptionException;

public interface IntegrationService {

  Agreement createAgreement(NewAgreement newAgreementDto)
      throws LetterFailedExceptionException, CreateCustomerFailedException,
          CreateAgreementFailedException, UpdateAgreementStatusFailedException,
          SendAgreementLetterFailedException;
}
