package fr.kevin.llps.ekw.customer.billing.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@Entity
@Table(name = "individual_customer")
public class IndividualCustomerEntity {

    public IndividualCustomerEntity(String referenceId, TitleEntity title, String lastname, String firstname) {
        this.referenceId = referenceId;
        this.title = title;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    @Id
    @GeneratedValue
    @Column(name = "reference_id", nullable = false, columnDefinition = "CHAR(12)")
    private String referenceId;

    @OneToOne
    @JoinColumn(name = "title_id")
    private TitleEntity title;

    @Column(name = "lastname", nullable = false, columnDefinition = "VARCHAR(200)")
    private String lastname;

    @Column(name = "firstname", nullable = false, columnDefinition = "VARCHAR(200)")
    private String firstname;

    @OneToMany(mappedBy = "individualCustomer", cascade = ALL)
    private List<IndividualCustomerEnergyEntity> individualCustomerEnergyEntities;

}
