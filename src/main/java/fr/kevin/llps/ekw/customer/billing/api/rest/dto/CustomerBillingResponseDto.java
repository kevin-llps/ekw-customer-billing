package fr.kevin.llps.ekw.customer.billing.api.rest.dto;

import java.math.BigDecimal;

public record CustomerBillingResponseDto(BigDecimal amount) {
}
