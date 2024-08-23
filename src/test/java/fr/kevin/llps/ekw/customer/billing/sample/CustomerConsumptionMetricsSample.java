package fr.kevin.llps.ekw.customer.billing.sample;

import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerConsumptionMetricsDto;
import fr.kevin.llps.ekw.customer.billing.domain.CustomerConsumptionMetrics;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerConsumptionMetricsSample {

    public static CustomerConsumptionMetrics oneCustomerConsumptionMetrics() {
        Map<String, BigDecimal> consumptionByEnergyTypes = Map.of(
                ELECTRICAL_ENERGY_TYPE, new BigDecimal("234.87"),
                GAS_ENERGY_TYPE, new BigDecimal("345.16"));

        return new CustomerConsumptionMetrics(consumptionByEnergyTypes);
    }

    public static List<CustomerConsumptionMetricsDto> customerConsumptionMetricsDtoList() {
        return List.of(
                new CustomerConsumptionMetricsDto(ELECTRICAL_ENERGY_TYPE, new BigDecimal("234.87")),
                new CustomerConsumptionMetricsDto(GAS_ENERGY_TYPE, new BigDecimal("345.16")));
    }

}
