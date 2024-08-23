package fr.kevin.llps.ekw.customer.billing.sample;

import fr.kevin.llps.ekw.customer.billing.api.rest.dto.CustomerBillingResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerBillingResponseDtoSample {

    public static CustomerBillingResponseDto oneCustomerBillingResponseDto() {
        BigDecimal amount = new BigDecimal("69.654");

        return new CustomerBillingResponseDto(amount);
    }

}
