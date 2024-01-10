package nz.co.pwd.feature.api;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Represents a feature flag model API class, for simplicity uses lombok
 * for immutable construction and builder pattern.
 */
@Value
@Builder
public class FeatureApi {
  private final String displayName;
  @NotNull
  private final String technicalName;
  private final LocalDateTime expiresOn;
  private final String description;
  @NotNull
  private final String[] customerIds;
}
