package fr.kevin.llps.ekw.customer.billing.api.rest;

import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerBillingRequestDto;
import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerBillingResponseDto;
import fr.kevin.llps.ekw.customer.billing.domain.Customer;
import fr.kevin.llps.ekw.customer.billing.domain.CustomerConsumptionMetrics;
import fr.kevin.llps.ekw.customer.billing.mapper.CustomerBillingResponseMapper;
import fr.kevin.llps.ekw.customer.billing.mapper.CustomerConsumptionMetricsMapper;
import fr.kevin.llps.ekw.customer.billing.domain.Invoice;
import fr.kevin.llps.ekw.customer.billing.service.CustomerBillingService;
import fr.kevin.llps.ekw.customer.billing.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static fr.kevin.llps.ekw.customer.billing.sample.CustomerBillingRequestDtoSample.oneCustomerBillingRequestDto;
import static fr.kevin.llps.ekw.customer.billing.sample.CustomerBillingResponseDtoSample.oneCustomerBillingResponseDto;
import static fr.kevin.llps.ekw.customer.billing.sample.CustomerConsumptionMetricsSample.oneCustomerConsumptionMetrics;
import static fr.kevin.llps.ekw.customer.billing.sample.IndividualCustomerSample.oneIndividualCustomer;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.readResource;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerBillingController.class)
class CustomerBillingControllerTest {

    private static final String CUSTOMER_ID = "EKW189765429";

    @Value("classpath:/json/customer-billing-request-ok.json")
    private Resource customerBillingRequest;

    @Value("classpath:/json/customer-billing-response-ok.json")
    private Resource customerBillingResponse;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerBillingService customerBillingService;

    @MockBean
    private CustomerConsumptionMetricsMapper customerConsumptionMetricsMapper;

    @MockBean
    private CustomerBillingResponseMapper customerBillingResponseMapper;

    @Test
    void shouldCompute() throws Exception {
        Customer customer = oneIndividualCustomer();
        CustomerBillingRequestDto customerBillingRequestDto = oneCustomerBillingRequestDto();
        CustomerBillingResponseDto customerBillingResponseDto = oneCustomerBillingResponseDto();
        CustomerConsumptionMetrics customerConsumptionMetrics = oneCustomerConsumptionMetrics();

        Invoice invoice = new Invoice(new BigDecimal("69.654"));

        when(customerService.getById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
        when(customerConsumptionMetricsMapper.map(customerBillingRequestDto.customerConsumptionMetrics())).thenReturn(customerConsumptionMetrics);
        when(customerBillingService.compute(customer, customerConsumptionMetrics)).thenReturn(invoice);
        when(customerBillingResponseMapper.map(invoice)).thenReturn(customerBillingResponseDto);

        mockMvc.perform(post("/customers/{id}/billing", CUSTOMER_ID)
                        .content(readResource(customerBillingRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(readResource(customerBillingResponse), true));

        verify(customerService).getById(CUSTOMER_ID);
        verify(customerConsumptionMetricsMapper).map(customerBillingRequestDto.customerConsumptionMetrics());
        verify(customerBillingService).compute(customer, customerConsumptionMetrics);
        verify(customerBillingResponseMapper).map(invoice);
        verifyNoMoreInteractions(customerService, customerConsumptionMetricsMapper, customerBillingService, customerBillingResponseMapper);
    }

    @Test
    void givenNoExistingId_shouldReturnNotFoundAsStatusCode() throws Exception {
        when(customerService.getById(CUSTOMER_ID)).thenReturn(Optional.empty());

        mockMvc.perform(post("/customers/{id}/billing", CUSTOMER_ID)
                        .content(readResource(customerBillingRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Customer " + CUSTOMER_ID + " was not found")));

        verify(customerService).getById(CUSTOMER_ID);
        verifyNoMoreInteractions(customerService);
        verifyNoInteractions(customerConsumptionMetricsMapper, customerBillingService, customerBillingResponseMapper);
    }

}
