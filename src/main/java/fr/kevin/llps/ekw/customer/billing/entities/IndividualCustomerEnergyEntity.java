package fr.kevin.llps.ekw.customer.billing.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "individual_customer_energy")
public class IndividualCustomerEnergyEntity {

    public IndividualCustomerEnergyEntity(IndividualCustomerEntity individualCustomer, EnergyTypeEntity energyType) {
        this.individualCustomer = individualCustomer;
        this.energyType = energyType;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, columnDefinition = "INTEGER")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "individual_customer_id")
    private IndividualCustomerEntity individualCustomer;

    @ManyToOne
    @JoinColumn(name = "energy_type_id")
    private EnergyTypeEntity energyType;

}
