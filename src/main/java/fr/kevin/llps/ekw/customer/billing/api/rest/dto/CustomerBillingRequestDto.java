package fr.kevin.llps.ekw.customer.billing.api.rest.dto;

import java.util.List;

public record CustomerBillingRequestDto(List<CustomerConsumptionMetricsDto> customerConsumptionMetrics) {
}
