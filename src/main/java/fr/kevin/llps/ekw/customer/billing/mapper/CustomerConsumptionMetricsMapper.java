package fr.kevin.llps.ekw.customer.billing.mapper;

import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerConsumptionMetricsDto;
import fr.kevin.llps.ekw.customer.billing.domain.CustomerConsumptionMetrics;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerConsumptionMetricsMapper {

    public CustomerConsumptionMetrics map(List<CustomerConsumptionMetricsDto> customerConsumptionMetrics) {
        Map<String, BigDecimal> consumptionByEnergyTypes = customerConsumptionMetrics.stream()
                .collect(Collectors.toMap(CustomerConsumptionMetricsDto::energyType, CustomerConsumptionMetricsDto::consumption));
        
        return new CustomerConsumptionMetrics(consumptionByEnergyTypes);
    }

}
