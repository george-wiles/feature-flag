package nz.co.pwd.feature.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Represents a feature flag model API class, for simplicity uses lombok
 * for immutable construction and builder pattern.
 */
@Builder
public record FeatureApi(Long id,
                         String displayName,
                         @NotNull String technicalName,
                         String description) {
  @JsonCreator
  public FeatureApi(
      @JsonProperty("id") Long id,
      @JsonProperty("displayName") String displayName,
      @JsonProperty("technicalName") String technicalName,
      @JsonProperty("description") String description) {
    this.id = id;
    this.displayName = displayName;
    this.technicalName = technicalName;
    this.description = description;
  }
}
