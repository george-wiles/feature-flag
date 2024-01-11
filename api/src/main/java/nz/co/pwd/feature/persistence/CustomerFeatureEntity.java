package nz.co.pwd.feature.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="CUSTOMER_FEATURE")
@Data
public class CustomerFeatureEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerEntity customer;

  @ManyToOne
  @JoinColumn(name = "feature_id")
  private FeatureEntity feature;

  @Column(name = "is_active")
  private boolean isActive;

  @Column(name = "is_inverted")
  private boolean isInverted;

  @Column(name = "expiration_date")
  private LocalDateTime expirationDate;
}
