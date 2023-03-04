package com.example.demo.services.integration;

import com.example.demo.services.business.exceptions.CreateAgreementFailed;
import com.example.demo.services.business.exceptions.CreateCustomerFailed;
import com.example.demo.services.business.exceptions.UpdateAgreementStatusFailed;
import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.integration.exceptions.SendAgreementLetterFailed;
import com.example.demo.services.integration.models.NewAgreement;
import com.example.demo.services.letter.exceptions.LetterFailedException;

public interface IntegrationService {

  Agreement createAgreement(NewAgreement newAgreementDto)
      throws LetterFailedException, CreateCustomerFailed, CreateAgreementFailed,
          UpdateAgreementStatusFailed, SendAgreementLetterFailed;
}
