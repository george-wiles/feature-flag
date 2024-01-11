package nz.co.pwd.feature.api.model;

import lombok.Builder;

import javax.validation.constraints.NotNull;

@Builder
public record CustomerFeatureApi(
    @NotNull String name,
    Boolean active,
    Boolean inverted,
    Boolean expired
) {
}
