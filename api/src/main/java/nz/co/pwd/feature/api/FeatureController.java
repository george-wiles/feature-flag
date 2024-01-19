package nz.co.pwd.feature.api;

import nz.co.pwd.feature.api.model.CustomerFeatureApi;
import nz.co.pwd.feature.api.model.CustomerFeatureListRequest;
import nz.co.pwd.feature.api.model.CustomerFeatureListResponse;
import nz.co.pwd.feature.api.model.CustomerFeatureUpdate;
import nz.co.pwd.feature.api.model.FeatureApi;
import nz.co.pwd.feature.persistence.CustomerFeatureEntity;
import nz.co.pwd.feature.persistence.FeatureEntity;
import nz.co.pwd.feature.service.FeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller responsible for feature flagging Rest API services to create,
 * update and archive a feature flag.
 * See (<a href="https://martinfowler.com/articles/feature-toggles.html">...</a>)
 */
@RequestMapping("api/v1")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FeatureController {
  private final static Logger logger = LoggerFactory.getLogger(FeatureController.class);
  private final FeatureService featureService;

  @Autowired
  FeatureController(FeatureService featureService) {
    this.featureService = featureService;
  }

  @PostMapping("feature")
  public ResponseEntity<FeatureApi> createFeatureFlag(
      @RequestBody FeatureApi request) {
    final FeatureEntity featureEntity = featureService.createFeature(request);
    final FeatureApi response =
        FeatureApi.builder()
            .id(featureEntity.getId())
            .displayName(featureEntity.getDisplayName())
            .technicalName(featureEntity.getTechnicalName())
            .description(featureEntity.getDescription())
            .build();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("features")
  public ResponseEntity<CustomerFeatureListResponse> listCustomerFeatures(
      @RequestBody CustomerFeatureListRequest request) {
    final List<CustomerFeatureEntity> customerFeatures =
        featureService.getCustomerFeatures(request);
    logger.info("Feature Service: " + customerFeatures);

    final List<CustomerFeatureApi> features =
        customerFeatures.stream()
            .map(entity -> {
              final LocalDateTime expiry = entity.getExpirationDate();
              return CustomerFeatureApi.builder()
                         .name(entity.getFeature().getDisplayName())
                         .active(entity.isActive())
                         .inverted(entity.isInverted())
                         .expired(expiry.isBefore(LocalDateTime.now()))
                         .build();
            })
            .collect(Collectors.toList());

    final CustomerFeatureListResponse response =
        CustomerFeatureListResponse.builder()
            .features(features.toArray(new CustomerFeatureApi[0])).build();
    logger.info("Response: " + features);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("feature/list")
  public ResponseEntity<List<FeatureApi>> getList() {
    final List<FeatureEntity> features = featureService.getFeatures();
    final List<FeatureApi> response =
        features.stream()
            .map(entity -> FeatureApi.builder()
                               .id(entity.getId())
                               .displayName(entity.getDisplayName())
                               .technicalName(entity.getTechnicalName())
                               .description(entity.getDescription()).build())
            .collect(Collectors.toList());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }


  @PutMapping("feature/{featureId}/customer/{customerId}")
  public ResponseEntity<CustomerFeatureApi> updateCustomerFeature(
      @PathVariable Long featureId,
      @PathVariable Long customerId,
      @RequestBody CustomerFeatureUpdate request
  ) {

    try {
      final CustomerFeatureEntity entity =
          this.featureService.updateCustomerFeature(featureId, customerId, request);
      final CustomerFeatureApi response =
          CustomerFeatureApi.builder()
              .active(entity.isActive())
              .inverted(entity.isInverted())
              .expired(entity.getExpirationDate().isBefore(LocalDateTime.now()))
              .build();
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (ChangeSetPersister.NotFoundException exception ) {
      logger.error(
          String.format("Error Customer Feature not found for, feature: %s, customer: %s",
              featureId, customerId), exception);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
