package nz.co.pwd.feature.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerFeatureRepository
    extends JpaRepository<CustomerFeatureEntity, Long> {
}
