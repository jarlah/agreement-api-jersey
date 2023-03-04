package com.example.demo.services.business.models;

import java.math.BigDecimal;
import java.util.UUID;

public record Agreement(
    UUID id, AgreementStatus status, BigDecimal agreementPrice, UUID customerId) {}
