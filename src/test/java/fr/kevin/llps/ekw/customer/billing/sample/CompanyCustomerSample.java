package fr.kevin.llps.ekw.customer.billing.sample;

import fr.kevin.llps.ekw.customer.billing.domain.CompanyCustomer;
import fr.kevin.llps.ekw.customer.billing.entities.CompanyCustomerEnergyEntity;
import fr.kevin.llps.ekw.customer.billing.entities.CompanyCustomerEntity;
import fr.kevin.llps.ekw.customer.billing.entities.EnergyTypeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static fr.kevin.llps.ekw.customer.billing.sample.EnergyTypeSample.energyTypeEntities;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyCustomerSample {

    public static CompanyCustomer oneCompanyCustomer(BigDecimal salesRevenue) {
        return new CompanyCustomer(
                "EKW189765456",
                "12343234565432",
                "Smart IT",
                salesRevenue,
                List.of(ELECTRICAL_ENERGY_TYPE, GAS_ENERGY_TYPE));
    }

    public static CompanyCustomerEntity oneCompanyCustomerEntity(BigDecimal salesRevenue) {
        CompanyCustomerEntity companyCustomerEntity = new CompanyCustomerEntity(
                "EKW189765456",
                "12343234565432",
                "Smart IT",
                salesRevenue);

        companyCustomerEntity.setCompanyCustomerEnergyEntities(companyCustomerEnergyEntities(companyCustomerEntity, energyTypeEntities()));

        return companyCustomerEntity;
    }

    private static List<CompanyCustomerEnergyEntity> companyCustomerEnergyEntities(CompanyCustomerEntity companyCustomer, List<EnergyTypeEntity> energyTypes) {
        return energyTypes.stream()
                .map(energyType -> new CompanyCustomerEnergyEntity(companyCustomer, energyType))
                .toList();
    }

}
