package fr.kevin.llps.ekw.customer.billing.domain;

import fr.kevin.llps.ekw.customer.billing.utils.TestUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static fr.kevin.llps.ekw.customer.billing.sample.CompanyCustomerSample.oneCompanyCustomer;
import static fr.kevin.llps.ekw.customer.billing.sample.CompanyPriceSample.companyPrices;
import static fr.kevin.llps.ekw.customer.billing.sample.CustomerConsumptionMetricsSample.oneCustomerConsumptionMetrics;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

class CompanyCustomerTest {

    @ParameterizedTest(name = "Expect invoice {1} for customer {0}")
    @MethodSource("provideCompanyCustomersAndConsumptionMetrics")
    void givenConsumptionMetricsAndPrices_shouldComputeInvoice(CompanyCustomer companyCustomer,
                                                               CustomerConsumptionMetrics consumptionMetrics,
                                                               Invoice expectedInvoice) {
        List<CompanyPrice> companyPrices = companyPrices();

        Map<String, CompanyPrice> companyPriceByEnergyTypes = Map.of(
                TestUtils.ELECTRICAL_ENERGY_TYPE, companyPrices.getFirst(),
                TestUtils.GAS_ENERGY_TYPE, companyPrices.getLast());

        Invoice actual = companyCustomer.compute(consumptionMetrics, companyPriceByEnergyTypes);

        assertThat(actual).isEqualTo(expectedInvoice);
    }

    private static Stream<Arguments> provideCompanyCustomersAndConsumptionMetrics() {
        CustomerConsumptionMetrics consumptionMetrics = oneCustomerConsumptionMetrics();

        CustomerConsumptionMetrics electricalConsumptionMetrics = new CustomerConsumptionMetrics(Map.of(
                ELECTRICAL_ENERGY_TYPE, new BigDecimal("234.87")));

        return Stream.of(
                Arguments.of(oneCompanyCustomer(new BigDecimal("565000.99")), consumptionMetrics, new Invoice(new BigDecimal("66.68916"))),
                Arguments.of(oneCompanyCustomer(new BigDecimal("1000000")), consumptionMetrics, new Invoice(new BigDecimal("66.68916"))),
                Arguments.of(oneCompanyCustomer(new BigDecimal("1000001")), consumptionMetrics, new Invoice(new BigDecimal("68.29038"))),
                Arguments.of(oneCompanyCustomer(new BigDecimal("1230691")), consumptionMetrics, new Invoice(new BigDecimal("68.29038"))),
                Arguments.of(oneCompanyCustomer(new BigDecimal("565000.99")), electricalConsumptionMetrics, new Invoice(new BigDecimal("26.30544"))));
    }

}
