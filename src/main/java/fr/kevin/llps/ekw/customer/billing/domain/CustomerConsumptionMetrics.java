package fr.kevin.llps.ekw.customer.billing.domain;

import java.math.BigDecimal;
import java.util.Map;

public record CustomerConsumptionMetrics(Map<String, BigDecimal> consumptionByEnergyTypes) {
}
