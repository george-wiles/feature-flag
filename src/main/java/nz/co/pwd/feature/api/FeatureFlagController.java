package nz.co.pwd.feature.api;

import nz.co.pwd.feature.persistence.FeatureEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for feature flagging Rest API services to create,
 * update and archive a feature flag.
 *
 * See (https://martinfowler.com/articles/feature-toggles.html)
 */
@RequestMapping("api/v1/feature")
@RestController
public class FeatureFlagController {
  private final static Logger logger = LoggerFactory.getLogger(FeatureFlagController.class);
  private final FeatureFlagService featureFlagService;

  @Autowired
  FeatureFlagController(FeatureFlagService featureFlagService) {
    this.featureFlagService = featureFlagService;
  }

  @PostMapping()
  public ResponseEntity<FeatureFlagApi> createFeatureFlag(
      @RequestBody FeatureFlagApi request) {

    final FeatureEntity featureEntity = featureFlagService.createFeature(request);
    final FeatureFlagApi response =
        FeatureFlagApi.builder()
            .displayName(featureEntity.getDisplayName())
            .technicalName(featureEntity.getTechnicalName())
            .description((featureEntity.getDescription()))
            .expiresOn(featureEntity.getExpiresOn())
            //.customerIds(featureEntity.getCustomerIds())
            .build();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("list")
  public ResponseEntity<FeatureFlagApi> getList(
      @RequestBody FeatureFlagApi request) {

    final FeatureEntity featureEntity = featureFlagService.createFeature(request);
    final FeatureFlagApi response =
        FeatureFlagApi.builder()
            .displayName(featureEntity.getDisplayName())
            .technicalName(featureEntity.getTechnicalName())
            .description((featureEntity.getDescription()))
            .expiresOn(featureEntity.getExpiresOn())
            //.customerIds(featureEntity.getCustomerIds())
            .build();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }






}
