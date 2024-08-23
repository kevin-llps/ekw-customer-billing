package fr.kevin.llps.ekw.customer.billing.sample;

import fr.kevin.llps.ekw.customer.billing.entities.EnergyTypeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnergyTypeSample {

    public static List<EnergyTypeEntity> energyTypeEntities() {
        return List.of(
                new EnergyTypeEntity(ELECTRICAL_ENERGY_TYPE),
                new EnergyTypeEntity(GAS_ENERGY_TYPE)
        );
    }

}
