package com.example.demo.services.letter;

import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.business.models.Customer;
import com.example.demo.services.letter.models.LetterStatus;

public class LetterServiceDummyImpl implements LetterService {
  @Override
  public LetterStatus sendAgreementLetterToCustomer(Agreement agreement, Customer customer) {
    return LetterStatus.SENT_OK;
  }
}
