package nz.co.pwd.feature.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import javax.validation.constraints.NotNull;


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

  @Column(length = 250)
  private String description;

  public FeatureEntity(String displayName,
                       String technicalName,
                       String description) {
    this.displayName = displayName;
    this.technicalName = technicalName;
    this.description = description;
  }

  public FeatureEntity() {
  }
}
