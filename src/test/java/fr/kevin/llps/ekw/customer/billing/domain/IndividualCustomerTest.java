package fr.kevin.llps.ekw.customer.billing.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static fr.kevin.llps.ekw.customer.billing.sample.CustomerConsumptionMetricsSample.oneCustomerConsumptionMetrics;
import static fr.kevin.llps.ekw.customer.billing.sample.IndividualCustomerSample.oneIndividualCustomer;
import static fr.kevin.llps.ekw.customer.billing.sample.IndividualPriceSample.individualPrices;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

class IndividualCustomerTest {

    @ParameterizedTest(name = "Expect invoice {1} for customer {0}")
    @MethodSource("provideConsumptionMetricsAndExpectedInvoice")
    void givenConsumptionMetricsAndPrices_shouldComputeInvoice(CustomerConsumptionMetrics consumptionMetrics, Invoice expectedInvoice) {
        List<IndividualPrice> individualPrices = individualPrices();
        IndividualCustomer individualCustomer = oneIndividualCustomer();

        Map<String, IndividualPrice> individualPriceByEnergyTypes = Map.of(
                ELECTRICAL_ENERGY_TYPE, individualPrices.getFirst(),
                GAS_ENERGY_TYPE, individualPrices.getLast());

        Invoice actual = individualCustomer.compute(consumptionMetrics, individualPriceByEnergyTypes);

        assertThat(actual).isEqualTo(expectedInvoice);
    }

    private static Stream<Arguments> provideConsumptionMetricsAndExpectedInvoice() {
        CustomerConsumptionMetrics consumptionMetrics = oneCustomerConsumptionMetrics();

        CustomerConsumptionMetrics electricalConsumptionMetrics = new CustomerConsumptionMetrics(Map.of(
                ELECTRICAL_ENERGY_TYPE, new BigDecimal("234.87")));

        return Stream.of(
                Arguments.of(consumptionMetrics, new Invoice(new BigDecimal("68.51499"))),
                Arguments.of(electricalConsumptionMetrics, new Invoice(new BigDecimal("31.23771"))));
    }

}
