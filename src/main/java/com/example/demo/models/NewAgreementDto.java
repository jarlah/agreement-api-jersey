package com.example.demo.models;

import com.example.demo.services.integration.models.NewAgreement;
import com.example.demo.util.validation.ValidCustomerPid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record NewAgreementDto(
    @ValidCustomerPid String customerPid,
    @Size(min = 1, max = 100) String customerName,
    // avoid horrendously large agreements
    @Min(1) @Max(100000000) BigDecimal agreementPrice,
    @NotNull LocalDate agreementDate) {
  public NewAgreement toServiceModel() {
    return new NewAgreement(customerPid, customerName, agreementPrice, agreementDate);
  }
}
