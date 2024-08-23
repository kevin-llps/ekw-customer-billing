package fr.kevin.llps.ekw.customer.billing.mapper;

import fr.kevin.llps.ekw.customer.billing.domain.IndividualPrice;
import fr.kevin.llps.ekw.customer.billing.entities.IndividualPriceEntity;
import org.junit.jupiter.api.Test;

import static fr.kevin.llps.ekw.customer.billing.sample.IndividualPriceEntitySample.oneIndividualPriceEntity;
import static fr.kevin.llps.ekw.customer.billing.sample.IndividualPriceSample.oneIndividualPrice;
import static org.assertj.core.api.Assertions.assertThat;

class IndividualPriceMapperTest {

    @Test
    void givenIndividualPriceEntity_shouldMap() {
        IndividualPriceEntity individualPriceEntity = oneIndividualPriceEntity();
        IndividualPrice expectedIndividualPrice = oneIndividualPrice();

        IndividualPriceMapper individualPriceMapper = new IndividualPriceMapper();

        IndividualPrice individualPrice = individualPriceMapper.map(individualPriceEntity);

        assertThat(individualPrice).isEqualTo(expectedIndividualPrice);
    }

}
