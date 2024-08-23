package fr.kevin.llps.ekw.customer.billing.sample;

import fr.kevin.llps.ekw.customer.billing.entities.EnergyTypeEntity;
import fr.kevin.llps.ekw.customer.billing.entities.IndividualPriceEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IndividualPriceEntitySample {

    public static List<IndividualPriceEntity> individualPriceEntities() {
        IndividualPriceEntity individualGasPriceEntity = new IndividualPriceEntity(
                new EnergyTypeEntity(GAS_ENERGY_TYPE),
                new BigDecimal("0.108"));

        return List.of(oneIndividualPriceEntity(), individualGasPriceEntity);
    }

    public static IndividualPriceEntity oneIndividualPriceEntity() {
        EnergyTypeEntity energyTypeEntity = new EnergyTypeEntity(ELECTRICAL_ENERGY_TYPE);

        return new IndividualPriceEntity(
                energyTypeEntity,
                new BigDecimal("0.133"));
    }

}
