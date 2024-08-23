package fr.kevin.llps.ekw.customer.billing.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "title")
public class TitleEntity {

    public TitleEntity(String designation) {
        this.designation = designation;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, columnDefinition = "INTEGER")
    private Integer id;

    @Column(name = "designation", nullable = false, columnDefinition = "VARCHAR(200)")
    private String designation;

}
