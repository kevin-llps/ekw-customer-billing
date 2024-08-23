package fr.kevin.llps.ekw.customer.billing.sample;

import fr.kevin.llps.ekw.customer.billing.entities.CompanyPriceEntity;
import fr.kevin.llps.ekw.customer.billing.entities.EnergyTypeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyPriceEntitySample {

    public static List<CompanyPriceEntity> companyPriceEntities() {
        CompanyPriceEntity companyGasPriceEntity = new CompanyPriceEntity(
                new EnergyTypeEntity(GAS_ENERGY_TYPE),
                new BigDecimal("1000000"),
                new BigDecimal("0.123"),
                new BigDecimal("0.117"));

        return List.of(oneCompanyPriceEntity(), companyGasPriceEntity);
    }

    public static CompanyPriceEntity oneCompanyPriceEntity() {
        return new CompanyPriceEntity(
                new EnergyTypeEntity(ELECTRICAL_ENERGY_TYPE),
                new BigDecimal("1000000"),
                new BigDecimal("0.112"),
                new BigDecimal("0.110"));
    }

}
