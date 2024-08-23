package fr.kevin.llps.ekw.customer.billing.sample;

import fr.kevin.llps.ekw.customer.billing.domain.IndividualCustomer;
import fr.kevin.llps.ekw.customer.billing.entities.EnergyTypeEntity;
import fr.kevin.llps.ekw.customer.billing.entities.IndividualCustomerEnergyEntity;
import fr.kevin.llps.ekw.customer.billing.entities.IndividualCustomerEntity;
import fr.kevin.llps.ekw.customer.billing.entities.TitleEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static fr.kevin.llps.ekw.customer.billing.sample.EnergyTypeSample.energyTypeEntities;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IndividualCustomerSample {

    public static IndividualCustomer oneIndividualCustomer() {
        return new IndividualCustomer(
                "EKW189765429",
                "Monsieur",
                "Parker",
                "Peter",
                List.of(ELECTRICAL_ENERGY_TYPE, GAS_ENERGY_TYPE));
    }

    public static IndividualCustomerEntity oneIndividualCustomerEntity() {
        IndividualCustomerEntity individualCustomerEntity = new IndividualCustomerEntity(
                "EKW189765429",
                new TitleEntity("Monsieur"),
                "Parker",
                "Peter");

        individualCustomerEntity.setIndividualCustomerEnergyEntities(individualCustomerEnergyEntities(individualCustomerEntity, energyTypeEntities()));

        return individualCustomerEntity;
    }

    private static List<IndividualCustomerEnergyEntity> individualCustomerEnergyEntities(IndividualCustomerEntity individualCustomer, List<EnergyTypeEntity> energyTypes) {
        return energyTypes.stream()
                .map(energyType -> new IndividualCustomerEnergyEntity(individualCustomer, energyType))
                .toList();
    }

}
