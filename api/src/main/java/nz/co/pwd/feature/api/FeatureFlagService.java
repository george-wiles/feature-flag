package nz.co.pwd.feature.api;

import nz.co.pwd.feature.persistence.FeatureEntity;
import nz.co.pwd.feature.persistence.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureFlagService {

  private final FeatureRepository featureRepository;

  @Autowired
  public FeatureFlagService(FeatureRepository featureRepository) {
    this.featureRepository = featureRepository;
  }

  public FeatureEntity createFeature(FeatureFlagApi api) {
    FeatureEntity newFeature = new FeatureEntity(api.getDisplayName(),
        api.getTechnicalName(),
        api.getExpiresOn(),
        api.getDescription());
    return featureRepository.save(newFeature);
  }
}
