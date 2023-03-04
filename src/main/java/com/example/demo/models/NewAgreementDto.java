package com.example.demo.models;

import com.example.demo.services.integration.models.NewAgreement;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record NewAgreementDto(
    // a norwegian pid is 11 numbers
    // TODO possible to actually validate the pid ?
    @Size(min = 1, max = 11) String customerPid,
    // limit name to 100 characters including space
    // Could be increased, but having a limit avoids a client sending in several megabytes ..
    @Size(min = 1, max = 100) String customerName,
    // avoid horrendously large agreements
    // Also, should there be some domain specific limit to how low the agreements can be?
    // For now, the agreement must be at least 1 NOK (not that this service cares about currency)
    @Min(1) @Max(100000000) BigDecimal agreementPrice) {
  public NewAgreement toServiceModel() {
    return new NewAgreement(customerPid, customerName, agreementPrice);
  }
}
