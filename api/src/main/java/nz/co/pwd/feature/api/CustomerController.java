package nz.co.pwd.feature.api;

import nz.co.pwd.feature.api.model.CustomerApi;
import nz.co.pwd.feature.persistence.CustomerEntity;
import nz.co.pwd.feature.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/v1/customer")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

  private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);
  private final CustomerService customerService;

  @Autowired
  CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostMapping()
  public ResponseEntity<CustomerApi> createCustomer(
      @RequestBody CustomerApi request) {

    final CustomerEntity customerEntity = customerService.createCustomer(request);
    final CustomerApi response =
        CustomerApi.builder()
            .name(customerEntity.getName())
            .id(customerEntity.getId())
            .build();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("list")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<CustomerApi>> getList() {
    final List<CustomerEntity> customers = customerService.getCustomers();
    final List<CustomerApi> response =
        customers.stream()
            .map(customerEntity -> CustomerApi.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName()).build())
        .collect(Collectors.toList());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
