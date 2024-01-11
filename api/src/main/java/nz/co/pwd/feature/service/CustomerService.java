package nz.co.pwd.feature.service;

import nz.co.pwd.feature.api.model.CustomerApi;
import nz.co.pwd.feature.persistence.CustomerEntity;
import nz.co.pwd.feature.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

  private final CustomerRepository repository;

  @Autowired
  public CustomerService(final CustomerRepository repository) {
    this.repository = repository;
  }

  public CustomerEntity createCustomer(final CustomerApi api) {
    CustomerEntity newCustomer = new CustomerEntity(api.name());
    return repository.save(newCustomer);
  }

  public List<CustomerEntity> getCustomers() {
    return repository.findAll();
  }

}
