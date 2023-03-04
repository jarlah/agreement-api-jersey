package com.example.demo.services.letter;

import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.business.models.Customer;
import com.example.demo.services.integration.exceptions.SendAgreementLetterFailedException;

public interface LetterService {
  void sendAgreementLetterToCustomer(Agreement agreement, Customer customer)
      throws SendAgreementLetterFailedException;
}
