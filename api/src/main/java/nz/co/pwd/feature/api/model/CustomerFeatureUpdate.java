package nz.co.pwd.feature.api.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CustomerFeatureUpdate (
  Long id,
  String name,
  Boolean active,
  Boolean inverted,
  LocalDateTime expirationDate) {
}
