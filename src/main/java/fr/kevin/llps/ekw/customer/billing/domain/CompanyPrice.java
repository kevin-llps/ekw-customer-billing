package fr.kevin.llps.ekw.customer.billing.domain;

import java.math.BigDecimal;

public record CompanyPrice(BigDecimal salesRevenueLimit,
                           BigDecimal firstPriceOffer,
                           BigDecimal secondPriceOffer,
                           String energyType) implements Price {
}
