package fr.kevin.llps.ekw.customer.billing.mapper;

import fr.kevin.llps.ekw.customer.billing.domain.CompanyPrice;
import fr.kevin.llps.ekw.customer.billing.entities.CompanyPriceEntity;
import org.junit.jupiter.api.Test;

import static fr.kevin.llps.ekw.customer.billing.sample.CompanyPriceEntitySample.oneCompanyPriceEntity;
import static fr.kevin.llps.ekw.customer.billing.sample.CompanyPriceSample.oneCompanyPrice;
import static org.assertj.core.api.Assertions.assertThat;

class CompanyPriceMapperTest {

    @Test
    void givenCompanyPriceEntity_shouldMap() {
        CompanyPriceEntity companyPriceEntity = oneCompanyPriceEntity();
        CompanyPrice expectedCompanyPrice = oneCompanyPrice();

        CompanyPriceMapper companyPriceMapper = new CompanyPriceMapper();

        CompanyPrice companyPrice = companyPriceMapper.map(companyPriceEntity);

        assertThat(companyPrice).isEqualTo(expectedCompanyPrice);
    }

}
