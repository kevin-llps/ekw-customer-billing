package fr.kevin.llps.ekw.customer.billing.sample;

import fr.kevin.llps.ekw.customer.billing.domain.IndividualPrice;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.ELECTRICAL_ENERGY_TYPE;
import static fr.kevin.llps.ekw.customer.billing.utils.TestUtils.GAS_ENERGY_TYPE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IndividualPriceSample {

    public static List<IndividualPrice> individualPrices() {
        IndividualPrice individualGasPrice = new IndividualPrice(
                new BigDecimal("0.108"),
                GAS_ENERGY_TYPE);

        return List.of(oneIndividualPrice(), individualGasPrice);
    }

    public static IndividualPrice oneIndividualPrice() {
        return new IndividualPrice(
                new BigDecimal("0.133"),
                ELECTRICAL_ENERGY_TYPE);
    }

}
