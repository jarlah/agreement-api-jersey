package com.example.demo.services.business.models;

import java.math.BigDecimal;
import java.util.UUID;

public record Agreement(
    // Serves as the "avtalenummer"
    UUID id,
    AgreementStatus status,
    BigDecimal agreementPrice,
    java.time.LocalDate agreementDate,
    UUID customerId) {}
