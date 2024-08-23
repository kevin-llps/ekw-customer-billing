package fr.kevin.llps.ekw.customer.billing.repository;

import fr.kevin.llps.ekw.customer.billing.entities.CompanyCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCustomerRepository extends JpaRepository<CompanyCustomerEntity, String> {
}
