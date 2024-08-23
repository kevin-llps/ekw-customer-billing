package fr.kevin.llps.ekw.customer.billing.service;

import fr.kevin.llps.ekw.customer.billing.domain.CompanyCustomer;
import fr.kevin.llps.ekw.customer.billing.domain.Customer;
import fr.kevin.llps.ekw.customer.billing.domain.IndividualCustomer;
import fr.kevin.llps.ekw.customer.billing.entities.CompanyCustomerEntity;
import fr.kevin.llps.ekw.customer.billing.entities.IndividualCustomerEntity;
import fr.kevin.llps.ekw.customer.billing.mapper.CustomerMapper;
import fr.kevin.llps.ekw.customer.billing.repository.CompanyCustomerRepository;
import fr.kevin.llps.ekw.customer.billing.repository.IndividualCustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static fr.kevin.llps.ekw.customer.billing.sample.CompanyCustomerSample.oneCompanyCustomer;
import static fr.kevin.llps.ekw.customer.billing.sample.CompanyCustomerSample.oneCompanyCustomerEntity;
import static fr.kevin.llps.ekw.customer.billing.sample.IndividualCustomerSample.oneIndividualCustomer;
import static fr.kevin.llps.ekw.customer.billing.sample.IndividualCustomerSample.oneIndividualCustomerEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private static final String CUSTOMER_ID = "EKW189765429";

    @Mock
    private CompanyCustomerRepository companyCustomerRepository;

    @Mock
    private IndividualCustomerRepository individualCustomerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void givenCompanyCustomerId_shouldGetById() {
        BigDecimal salesRevenue = new BigDecimal("1000000");
        CompanyCustomerEntity companyCustomerEntity = oneCompanyCustomerEntity(salesRevenue);
        CompanyCustomer companyCustomer = oneCompanyCustomer(salesRevenue);

        when(companyCustomerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(companyCustomerEntity));
        when(customerMapper.map(companyCustomerEntity)).thenReturn(companyCustomer);

        Optional<Customer> optionalCustomer = customerService.getById(CUSTOMER_ID);

        assertThat(optionalCustomer).hasValue(companyCustomer);

        verify(companyCustomerRepository).findById(CUSTOMER_ID);
        verify(customerMapper).map(companyCustomerEntity);
        verifyNoMoreInteractions(companyCustomerRepository, customerMapper);
        verifyNoInteractions(individualCustomerRepository);
    }

    @Test
    void givenIndividualCustomerId_shouldGetById() {
        IndividualCustomerEntity individualCustomerEntity = oneIndividualCustomerEntity();
        IndividualCustomer individualCustomer = oneIndividualCustomer();

        when(companyCustomerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.empty());
        when(individualCustomerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(individualCustomerEntity));
        when(customerMapper.map(individualCustomerEntity)).thenReturn(individualCustomer);

        Optional<Customer> optionalCustomer = customerService.getById(CUSTOMER_ID);

        assertThat(optionalCustomer).hasValue(individualCustomer);

        verify(companyCustomerRepository).findById(CUSTOMER_ID);
        verify(customerMapper).map(individualCustomerEntity);
        verifyNoMoreInteractions(companyCustomerRepository, individualCustomerRepository, customerMapper);
    }

}
