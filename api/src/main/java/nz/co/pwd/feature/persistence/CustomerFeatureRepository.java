package nz.co.pwd.feature.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerFeatureRepository
    extends JpaRepository<CustomerFeatureEntity, Long> {

  List<CustomerFeatureEntity> findByCustomerIdAndFeatureDisplayNameIn(Long customerId, List<String> names);

}
