package nz.co.pwd.feature.api.model;

import lombok.Builder;

@Builder
public record CustomerFeatureRequestApi(
    Long customerId,
    CustomerFeatureApi[] features) {
}
