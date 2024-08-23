package fr.kevin.llps.ekw.customer.billing.sample;

import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerBillingRequestDto;
import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerConsumptionMetricsDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerBillingRequestDtoSample {

    public static CustomerBillingRequestDto oneCustomerBillingRequestDto() {
        List<CustomerConsumptionMetricsDto> customerConsumptionMetrics = List.of(
                new CustomerConsumptionMetricsDto(ELECTRICAL_ENERGY_TYPE, new BigDecimal("234.87")),
                new CustomerConsumptionMetricsDto(GAS_ENERGY_TYPE, new BigDecimal("345.16")));

        return new CustomerBillingRequestDto(customerConsumptionMetrics);
    }

}
