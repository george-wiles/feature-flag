package nz.co.pwd.feature.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="FEATURE")
@Data
public class FeatureEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 250)
  private String displayName;

  @NotNull
  @Column(length = 250)
  private String technicalName;

  private LocalDateTime expiresOn;

  @Column(length = 250)
  private String description;

  public FeatureEntity(String displayName,
                       String technicalName,
                       LocalDateTime expiresOn,
                       String description) {
    this.displayName = displayName;
    this.technicalName = technicalName;
    this.expiresOn = expiresOn;
    this.description = description;
  }
}
