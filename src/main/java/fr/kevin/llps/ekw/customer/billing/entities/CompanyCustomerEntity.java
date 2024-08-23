package fr.kevin.llps.ekw.customer.billing.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@Entity
@Table(name = "company_customer")
public class CompanyCustomerEntity {

    public CompanyCustomerEntity(String referenceId, String siret, String name, BigDecimal salesRevenue) {
        this.referenceId = referenceId;
        this.siret = siret;
        this.name = name;
        this.salesRevenue = salesRevenue;
    }

    @Id
    @GeneratedValue
    @Column(name = "reference_id", nullable = false, columnDefinition = "CHAR(12)")
    private String referenceId;

    @Column(name = "siret", nullable = false, columnDefinition = "CHAR(14)")
    private String siret;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(200)")
    private String name;

    @Column(name = "sales_revenue", nullable = false, columnDefinition = "NUMERIC")
    private BigDecimal salesRevenue;

    @OneToMany(mappedBy = "companyCustomer", cascade = ALL)
    private List<CompanyCustomerEnergyEntity> companyCustomerEnergyEntities;

}
