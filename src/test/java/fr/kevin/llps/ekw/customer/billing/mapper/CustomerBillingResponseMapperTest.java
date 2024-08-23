package fr.kevin.llps.ekw.customer.billing.mapper;

import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerBillingResponseDto;
import fr.kevin.llps.ekw.customer.billing.domain.Invoice;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerBillingResponseMapperTest {

    @Test
    void shouldMap() {
        BigDecimal expectedAmount = new BigDecimal("66.68916");
        
        Invoice invoice = new Invoice(expectedAmount);

        CustomerBillingResponseMapper customerBillingResponseMapper = new CustomerBillingResponseMapper();

        CustomerBillingResponseDto customerBillingResponseDto = customerBillingResponseMapper.map(invoice);

        assertThat(customerBillingResponseDto.amount()).isEqualTo(expectedAmount);
    }

}
