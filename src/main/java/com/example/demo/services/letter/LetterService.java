package com.example.demo.services.letter;

import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.business.models.Customer;
import com.example.demo.services.letter.exceptions.LetterFailedExceptionException;
import com.example.demo.services.letter.models.LetterStatus;

public interface LetterService {
  LetterStatus sendAgreementLetterToCustomer(Agreement agreement, Customer customer)
      throws LetterFailedExceptionException;
}
