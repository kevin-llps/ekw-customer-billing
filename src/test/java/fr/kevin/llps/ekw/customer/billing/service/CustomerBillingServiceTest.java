package fr.kevin.llps.ekw.customer.billing.service;

import fr.kevin.llps.ekw.customer.billing.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static fr.kevin.llps.ekw.customer.billing.sample.CompanyPriceSample.companyPrices;
import static fr.kevin.llps.ekw.customer.billing.sample.CustomerConsumptionMetricsSample.oneCustomerConsumptionMetrics;
import static fr.kevin.llps.ekw.customer.billing.sample.IndividualPriceSample.individualPrices;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerBillingServiceTest {

    @Mock
    private CompanyPriceService companyPriceService;

    @Mock
    private IndividualPriceService individualPriceService;

    @InjectMocks
    private CustomerBillingService customerBillingService;

    @Test
    void givenCompanyCustomer_shouldComputeInvoice() {
        CompanyCustomer companyCustomer = mock(CompanyCustomer.class);
        List<CompanyPrice> companyPrices = companyPrices();
        CustomerConsumptionMetrics consumptionMetrics = oneCustomerConsumptionMetrics();
        Invoice expectedInvoice = new Invoice(new BigDecimal("66.68916"));

        Map<String, CompanyPrice> companyPriceByEnergyTypes = Map.of(
                ELECTRICAL_ENERGY_TYPE, companyPrices.getFirst(),
                GAS_ENERGY_TYPE, companyPrices.getLast());

        when(companyPriceService.getCompanyPriceByEnergyTypes()).thenReturn(companyPriceByEnergyTypes);
        when(companyCustomer.compute(consumptionMetrics, companyPriceByEnergyTypes)).thenReturn(expectedInvoice);

        Invoice actual = customerBillingService.compute(companyCustomer, consumptionMetrics);

        assertThat(actual).isEqualTo(expectedInvoice);

        verify(companyPriceService).getCompanyPriceByEnergyTypes();
        verify(companyCustomer).compute(consumptionMetrics, companyPriceByEnergyTypes);
        verifyNoMoreInteractions(companyPriceService, companyCustomer);
    }

    @Test
    void givenIndividualCustomer_shouldComputeInvoice() {
        IndividualCustomer individualCustomer = mock(IndividualCustomer.class);
        List<IndividualPrice> individualPrices = individualPrices();
        CustomerConsumptionMetrics consumptionMetrics = oneCustomerConsumptionMetrics();
        Invoice expectedInvoice = new Invoice(new BigDecimal("66.68916"));

        Map<String, IndividualPrice> individualPriceByEnergyTypes = Map.of(
                ELECTRICAL_ENERGY_TYPE, individualPrices.getFirst(),
                GAS_ENERGY_TYPE, individualPrices.getLast());

        when(individualPriceService.getIndividualPriceByEnergyTypes()).thenReturn(individualPriceByEnergyTypes);
        when(individualCustomer.compute(consumptionMetrics, individualPriceByEnergyTypes)).thenReturn(expectedInvoice);

        Invoice actual = customerBillingService.compute(individualCustomer, consumptionMetrics);

        assertThat(actual).isEqualTo(expectedInvoice);

        verify(individualPriceService).getIndividualPriceByEnergyTypes();
        verify(individualCustomer).compute(consumptionMetrics, individualPriceByEnergyTypes);
        verifyNoMoreInteractions(individualPriceService, individualCustomer);
    }

}
