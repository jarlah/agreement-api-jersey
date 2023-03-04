package com.example.demo.services.letter;

import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.business.models.Customer;

public class LetterServiceDummyImpl implements LetterService {
  @Override
  public void sendAgreementLetterToCustomer(Agreement agreement, Customer customer) {}
}
