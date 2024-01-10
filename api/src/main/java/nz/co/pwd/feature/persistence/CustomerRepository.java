package nz.co.pwd.feature.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
    extends JpaRepository<CustomerEntity, Long> {
}
