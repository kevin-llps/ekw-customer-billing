package fr.kevin.llps.ekw.customer.billing.service;

import fr.kevin.llps.ekw.customer.billing.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerBillingService {

    private final CompanyPriceService companyPriceService;
    private final IndividualPriceService individualPriceService;

    public Invoice compute(Customer customer, CustomerConsumptionMetrics consumptionMetrics) {
        return switch (customer) {
            case CompanyCustomer companyCustomer ->
                    companyCustomer.compute(consumptionMetrics, companyPriceService.getCompanyPriceByEnergyTypes());
            case IndividualCustomer individualCustomer ->
                    individualCustomer.compute(consumptionMetrics, individualPriceService.getIndividualPriceByEnergyTypes());
        };
    }

}
