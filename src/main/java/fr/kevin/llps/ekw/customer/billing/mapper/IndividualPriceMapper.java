package fr.kevin.llps.ekw.customer.billing.mapper;

import fr.kevin.llps.ekw.customer.billing.domain.IndividualPrice;
import fr.kevin.llps.ekw.customer.billing.entities.IndividualPriceEntity;
import org.springframework.stereotype.Service;

@Service
public class IndividualPriceMapper {

    public IndividualPrice map(IndividualPriceEntity individualPriceEntity) {
        return new IndividualPrice(
                individualPriceEntity.getPrice(),
                individualPriceEntity.getEnergyType().getDesignation());
    }

}
