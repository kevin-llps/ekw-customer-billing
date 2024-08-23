package fr.kevin.llps.ekw.customer.billing.sample;

import fr.kevin.llps.ekw.customer.billing.domain.CompanyPrice;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyPriceSample {

    public static List<CompanyPrice> companyPrices() {
        CompanyPrice companyGasPrice = new CompanyPrice(
                new BigDecimal("1000000"),
                new BigDecimal("0.117"),
                new BigDecimal("0.123"),
                GAS_ENERGY_TYPE);

        return List.of(oneCompanyPrice(), companyGasPrice);
    }

    public static CompanyPrice oneCompanyPrice() {
        return new CompanyPrice(
                new BigDecimal("1000000"),
                new BigDecimal("0.112"),
                new BigDecimal("0.110"),
                ELECTRICAL_ENERGY_TYPE);
    }

}
