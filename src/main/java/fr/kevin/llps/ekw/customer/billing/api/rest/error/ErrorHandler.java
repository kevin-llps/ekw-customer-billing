package fr.kevin.llps.ekw.customer.billing.api.rest.error;

import fr.kevin.llps.ekw.customer.billing.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorDto handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException) {
        return new ErrorDto(customerNotFoundException.getMessage());
    }

}
