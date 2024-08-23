package fr.kevin.llps.ekw.customer.billing.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "company_customer_energy")
public class CompanyCustomerEnergyEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, columnDefinition = "INTEGER")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "company_customer_id")
    private CompanyCustomerEntity companyCustomer;

    @ManyToOne
    @JoinColumn(name = "energy_type_id")
    private EnergyTypeEntity energyType;

}
