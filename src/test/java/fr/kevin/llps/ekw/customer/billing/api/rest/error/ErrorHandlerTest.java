package fr.kevin.llps.ekw.customer.billing.api.rest.error;

import fr.kevin.llps.ekw.customer.billing.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorHandlerTest {

    private static final String ERROR_MESSAGE = "message";

    @Test
    void shouldHandleCustomerNotFoundException() {
        ErrorHandler errorHandler = new ErrorHandler();

        ErrorDto errorDto = errorHandler.handleCustomerNotFoundException(new CustomerNotFoundException(ERROR_MESSAGE));

        assertThat(errorDto.message()).isEqualTo(ERROR_MESSAGE);
    }

}
