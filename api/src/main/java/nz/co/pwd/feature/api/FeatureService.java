package nz.co.pwd.feature.api;

import nz.co.pwd.feature.persistence.FeatureEntity;
import nz.co.pwd.feature.persistence.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {

  private final FeatureRepository featureRepository;

  @Autowired
  public FeatureService(FeatureRepository featureRepository) {
    this.featureRepository = featureRepository;
  }

  public FeatureEntity createFeature(FeatureApi api) {
    FeatureEntity newFeature = new FeatureEntity(api.getDisplayName(),
        api.getTechnicalName(),
        api.getExpiresOn(),
        api.getDescription());
    return featureRepository.save(newFeature);
  }
}
