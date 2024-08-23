package fr.kevin.llps.ekw.customer.billing.mapper;

import fr.kevin.llps.ekw.customer.billing.domain.CompanyCustomer;
import fr.kevin.llps.ekw.customer.billing.domain.Customer;
import fr.kevin.llps.ekw.customer.billing.domain.IndividualCustomer;
import fr.kevin.llps.ekw.customer.billing.entities.CompanyCustomerEntity;
import fr.kevin.llps.ekw.customer.billing.entities.IndividualCustomerEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static fr.kevin.llps.ekw.customer.billing.sample.CompanyCustomerSample.oneCompanyCustomer;
import static fr.kevin.llps.ekw.customer.billing.sample.CompanyCustomerSample.oneCompanyCustomerEntity;
import static fr.kevin.llps.ekw.customer.billing.sample.IndividualCustomerSample.oneIndividualCustomer;
import static fr.kevin.llps.ekw.customer.billing.sample.IndividualCustomerSample.oneIndividualCustomerEntity;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerMapperTest {

    @Test
    void givenCompanyCustomerEntity_shouldMap() {
        BigDecimal salesRevenue = new BigDecimal("1000000");

        CompanyCustomer expectedCompanyCustomer = oneCompanyCustomer(salesRevenue);

        CompanyCustomerEntity companyCustomerEntity = oneCompanyCustomerEntity(salesRevenue);

        CustomerMapper customerMapper = new CustomerMapper();

        Customer customer = customerMapper.map(companyCustomerEntity);

        assertThat(customer).isEqualTo(expectedCompanyCustomer);
    }

    @Test
    void givenIndividualCustomerEntity_shouldMap() {
        IndividualCustomer expectedIndividualCustomer = oneIndividualCustomer();

        IndividualCustomerEntity individualCustomerEntity = oneIndividualCustomerEntity();

        CustomerMapper customerMapper = new CustomerMapper();

        Customer customer = customerMapper.map(individualCustomerEntity);

        assertThat(customer).isEqualTo(expectedIndividualCustomer);
    }

}
