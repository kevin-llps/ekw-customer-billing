package fr.kevin.llps.ekw.customer.billing.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "company_price")
public class CompanyPriceEntity {

    public CompanyPriceEntity(EnergyTypeEntity energyType,
                              BigDecimal salesRevenueLimit,
                              BigDecimal firstPriceOffer,
                              BigDecimal secondPriceOffer) {
        this.energyType = energyType;
        this.salesRevenueLimit = salesRevenueLimit;
        this.firstPriceOffer = firstPriceOffer;
        this.secondPriceOffer = secondPriceOffer;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, columnDefinition = "INTEGER")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "energy_type_id")
    private EnergyTypeEntity energyType;

    @Column(name = "sales_revenue_limit", nullable = false, columnDefinition = "NUMERIC")
    private BigDecimal salesRevenueLimit;

    @Column(name = "first_price_offer", nullable = false, columnDefinition = "NUMERIC")
    private BigDecimal firstPriceOffer;

    @Column(name = "second_price_offer", nullable = false, columnDefinition = "NUMERIC")
    private BigDecimal secondPriceOffer;

}
