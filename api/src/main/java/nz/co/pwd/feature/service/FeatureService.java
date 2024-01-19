package nz.co.pwd.feature.service;

import nz.co.pwd.feature.api.model.CustomerFeatureApi;
import nz.co.pwd.feature.api.model.CustomerFeatureListRequest;
import nz.co.pwd.feature.api.model.CustomerFeatureUpdate;
import nz.co.pwd.feature.api.model.FeatureApi;
import nz.co.pwd.feature.persistence.CustomerFeatureEntity;
import nz.co.pwd.feature.persistence.CustomerFeatureRepository;
import nz.co.pwd.feature.persistence.FeatureEntity;
import nz.co.pwd.feature.persistence.FeatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureService {
  private final static Logger logger = LoggerFactory.getLogger(FeatureService.class);

  private final FeatureRepository featureRepository;
  private final CustomerFeatureRepository customerFeatureRepository;

  @Autowired
  public FeatureService(FeatureRepository featureRepository,
                        CustomerFeatureRepository customerFeatureRepository) {
    this.featureRepository = featureRepository;
    this.customerFeatureRepository = customerFeatureRepository;
  }

  public FeatureEntity createFeature(FeatureApi api) {
    FeatureEntity newFeature = new FeatureEntity(
        api.displayName(),
        api.technicalName(),
        api.description());
    return featureRepository.save(newFeature);
  }

  public List<FeatureEntity> getFeatures() {
    return featureRepository.findAll();
  }

  public List<CustomerFeatureEntity> getCustomerFeatures(CustomerFeatureListRequest request) {
    final List<String> names =
        Arrays.stream(request.features())
            .map(feature -> feature.name())
            .collect(Collectors.toList());

    final List<CustomerFeatureEntity> features = customerFeatureRepository
               .findByCustomerIdAndFeatureDisplayNameIn(
                   request.customerId(), names);
    logger.info("JPA findByCustomerIdAndFeatureDisplayNameIn returns " + features);
    return features;
  }

  public CustomerFeatureEntity updateCustomerFeature(Long featureId,
                                                  Long customerId,
                                                  CustomerFeatureUpdate api)
      throws ChangeSetPersister.NotFoundException {

    // Fetch the existing customer feature from the database
    CustomerFeatureEntity existingCustomerFeature =
      customerFeatureRepository.findByFeatureIdAndCustomerId(featureId, customerId);

    if (existingCustomerFeature != null) {
      // Update the fields based on the request DTO
     // existingCustomerFeature.set(api.name());
      existingCustomerFeature.setActive(api.active());
      existingCustomerFeature.setInverted(api.inverted());
      existingCustomerFeature.setExpirationDate(api.expirationDate());

      // Save the updated customer feature
      return customerFeatureRepository.save(existingCustomerFeature);
    } else {
      // Handle the case where the customer feature is not found
      throw new ChangeSetPersister.NotFoundException();
    }
  }
}
