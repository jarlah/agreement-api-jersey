package com.example.demo.services.integration.models;

import java.math.BigDecimal;

public record NewAgreement(
    String customerPid,
    String customerName,
    BigDecimal agreementPrice,
    java.time.LocalDate agreementDate) {}
