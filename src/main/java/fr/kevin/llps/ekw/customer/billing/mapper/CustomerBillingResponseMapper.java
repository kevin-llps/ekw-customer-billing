package fr.kevin.llps.ekw.customer.billing.mapper;

import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerBillingResponseDto;
import fr.kevin.llps.ekw.customer.billing.domain.Invoice;
import org.springframework.stereotype.Service;

@Service
public class CustomerBillingResponseMapper {

    public CustomerBillingResponseDto map(Invoice invoice) {
        return new CustomerBillingResponseDto(invoice.amount());
    }

}
