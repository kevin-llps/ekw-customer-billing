package fr.kevin.llps.ekw.customer.billing.mapper;

import fr.kevin.llps.ekw.customer.billing.domain.CompanyCustomer;
import fr.kevin.llps.ekw.customer.billing.domain.Customer;
import fr.kevin.llps.ekw.customer.billing.domain.IndividualCustomer;
import fr.kevin.llps.ekw.customer.billing.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMapper {

    public Customer map(CompanyCustomerEntity companyCustomerEntity) {
        return new CompanyCustomer(companyCustomerEntity.getReferenceId(),
                companyCustomerEntity.getSiret(),
                companyCustomerEntity.getName(),
                companyCustomerEntity.getSalesRevenue(),
                getEnergyTypes(companyCustomerEntity));
    }

    private List<String> getEnergyTypes(CompanyCustomerEntity companyCustomerEntity) {
        return companyCustomerEntity.getCompanyCustomerEnergyEntities()
                .stream()
                .map(CompanyCustomerEnergyEntity::getEnergyType)
                .map(EnergyTypeEntity::getDesignation)
                .toList();
    }

    public Customer map(IndividualCustomerEntity individualCustomerEntity) {
        return new IndividualCustomer(individualCustomerEntity.getReferenceId(),
                individualCustomerEntity.getTitle().getDesignation(),
                individualCustomerEntity.getLastname(),
                individualCustomerEntity.getFirstname(),
                getEnergyTypes(individualCustomerEntity));
    }

    private List<String> getEnergyTypes(IndividualCustomerEntity individualCustomerEntity) {
        return individualCustomerEntity.getIndividualCustomerEnergyEntities()
                .stream()
                .map(IndividualCustomerEnergyEntity::getEnergyType)
                .map(EnergyTypeEntity::getDesignation)
                .toList();
    }

}
