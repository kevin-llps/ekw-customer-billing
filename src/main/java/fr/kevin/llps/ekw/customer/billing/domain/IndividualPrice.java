package fr.kevin.llps.ekw.customer.billing.domain;

import java.math.BigDecimal;

public record IndividualPrice(BigDecimal price,
                              String energyType)  implements Price{
}
