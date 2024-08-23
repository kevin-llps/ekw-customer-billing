package fr.kevin.llps.ekw.customer.billing.repository;

import fr.kevin.llps.ekw.customer.billing.entities.IndividualCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomerEntity, String> {
}
