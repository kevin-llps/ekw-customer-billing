package fr.kevin.llps.ekw.customer.billing.mapper;

import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerConsumptionMetricsDto;
import fr.kevin.llps.ekw.customer.billing.domain.CustomerConsumptionMetrics;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.kevin.llps.ekw.customer.billing.sample.CustomerConsumptionMetricsSample.customerConsumptionMetricsDtoList;
import static fr.kevin.llps.ekw.customer.billing.sample.CustomerConsumptionMetricsSample.oneCustomerConsumptionMetrics;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerConsumptionMetricsMapperTest {

    @Test
    void shouldMap() {
        List<CustomerConsumptionMetricsDto> customerConsumptionMetricsDtoList = customerConsumptionMetricsDtoList();
        CustomerConsumptionMetrics expectedConsumptionMetrics = oneCustomerConsumptionMetrics();

        CustomerConsumptionMetricsMapper customerConsumptionMetricsMapper = new CustomerConsumptionMetricsMapper();

        CustomerConsumptionMetrics consumptionMetrics = customerConsumptionMetricsMapper.map(customerConsumptionMetricsDtoList);

        assertThat(consumptionMetrics).isEqualTo(expectedConsumptionMetrics);
    }

}
