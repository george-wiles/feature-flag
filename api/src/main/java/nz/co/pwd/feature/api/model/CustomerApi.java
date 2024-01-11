package nz.co.pwd.feature.api.model;

import lombok.Builder;

@Builder
public record CustomerApi(Long id, String name) {
}
