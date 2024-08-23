package fr.kevin.llps.ekw.customer.billing.mapper;

import fr.kevin.llps.ekw.customer.billing.domain.CompanyPrice;
import fr.kevin.llps.ekw.customer.billing.entities.CompanyPriceEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyPriceMapper {

    public CompanyPrice map(CompanyPriceEntity companyPriceEntity) {
        return new CompanyPrice(
                companyPriceEntity.getSalesRevenueLimit(),
                companyPriceEntity.getFirstPriceOffer(),
                companyPriceEntity.getSecondPriceOffer(),
                companyPriceEntity.getEnergyType().getDesignation());
    }

}
