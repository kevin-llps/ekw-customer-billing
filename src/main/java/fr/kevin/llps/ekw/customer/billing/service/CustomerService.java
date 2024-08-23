package fr.kevin.llps.ekw.customer.billing.service;

import fr.kevin.llps.ekw.customer.billing.domain.Customer;
import fr.kevin.llps.ekw.customer.billing.mapper.CustomerMapper;
import fr.kevin.llps.ekw.customer.billing.repository.CompanyCustomerRepository;
import fr.kevin.llps.ekw.customer.billing.repository.IndividualCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CompanyCustomerRepository companyCustomerRepository;
    private final IndividualCustomerRepository individualCustomerRepository;
    private final CustomerMapper customerMapper;

    public Optional<Customer> getById(String id) {
        return companyCustomerRepository.findById(id)
                .map(customerMapper::map)
                .or(() -> individualCustomerRepository.findById(id).map(customerMapper::map));
    }

}
