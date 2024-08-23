package fr.kevin.llps.ekw.customer.billing.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "individual_price")
public class IndividualPriceEntity {

    public IndividualPriceEntity(EnergyTypeEntity energyType, BigDecimal price) {
        this.energyType = energyType;
        this.price = price;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, columnDefinition = "INTEGER")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "energy_type_id")
    private EnergyTypeEntity energyType;

    @Column(name = "price", nullable = false, columnDefinition = "NUMERIC")
    private BigDecimal price;

}
