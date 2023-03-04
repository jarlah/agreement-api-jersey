package com.example.demo.services.business;

import com.example.demo.services.business.exceptions.CreateAgreementFailedException;
import com.example.demo.services.business.exceptions.CreateCustomerFailedException;
import com.example.demo.services.business.exceptions.UpdateAgreementStatusFailedException;
import com.example.demo.services.business.models.Agreement;
import com.example.demo.services.business.models.AgreementStatus;
import com.example.demo.services.business.models.Customer;
import java.math.BigDecimal;
import java.util.UUID;

/** This service might call out to an external api */
public interface BusinessService {

  Customer createCustomer(String customerPid, String name) throws CreateCustomerFailedException;

  Agreement createAgreement(UUID customerId, BigDecimal agreementPrice)
      throws CreateAgreementFailedException;

  Agreement updateAgreementStatus(Agreement agreement, AgreementStatus agreementStatus)
      throws UpdateAgreementStatusFailedException;
}
