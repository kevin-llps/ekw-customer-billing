package fr.kevin.llps.ekw.customer.billing.api.rest;

import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerBillingRequestDto;
import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerBillingResponseDto;
import fr.kevin.llps.ekw.customer.billing.domain.Customer;
import fr.kevin.llps.ekw.customer.billing.domain.CustomerConsumptionMetrics;
import fr.kevin.llps.ekw.customer.billing.exception.CustomerNotFoundException;
import fr.kevin.llps.ekw.customer.billing.mapper.CustomerBillingResponseMapper;
import fr.kevin.llps.ekw.customer.billing.mapper.CustomerConsumptionMetricsMapper;
import fr.kevin.llps.ekw.customer.billing.domain.Invoice;
import fr.kevin.llps.ekw.customer.billing.service.CustomerBillingService;
import fr.kevin.llps.ekw.customer.billing.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerBillingController {

    private final CustomerService customerService;
    private final CustomerBillingService customerBillingService;
    private final CustomerConsumptionMetricsMapper customerConsumptionMetricsMapper;
    private final CustomerBillingResponseMapper customerBillingResponseMapper;

    @PostMapping("{id}/billing")
    public CustomerBillingResponseDto compute(@PathVariable String id,
                                              @RequestBody CustomerBillingRequestDto customerBillingRequestDto) {

        Customer customer = customerService.getById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer " + id + " was not found"));

        CustomerConsumptionMetrics customerConsumptionMetrics = customerConsumptionMetricsMapper.map(customerBillingRequestDto.customerConsumptionMetrics());

        Invoice invoice = customerBillingService.compute(customer, customerConsumptionMetrics);

        return customerBillingResponseMapper.map(invoice);
    }

}
