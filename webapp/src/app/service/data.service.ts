// data.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AddCustomer, Customer } from '../model/customer.model';
import { AddFeature, CustomerFeature, CustomerFeatureRequest, CustomerFeatureResponse, Feature } from '../model/feature.model';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private baseUrl = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) {}

  getCustomers(): Observable<Customer[]> {
    const url = `${this.baseUrl}/customer/list`;
    return this.http.get<Customer[]>(url);
  }

  createCustomer(data: AddCustomer): Observable<Customer> {
    const url = `${this.baseUrl}/customer`;
    return this.http.post<Customer>(url, data);
  }

  getFeatures(): Observable<Feature[]> {
    const url = `${this.baseUrl}/feature/list`;
    return this.http.get<Feature[]>(url);
  }

  getCustomerFeatures(data: CustomerFeatureRequest): Observable<CustomerFeatureResponse> {
    const url = `${this.baseUrl}/features`;
    return this.http.post<CustomerFeatureResponse>(url, data);
  }

  createFeature(data: AddFeature): Observable<Feature> {
    const url = `${this.baseUrl}/feature`;
    return this.http.post<Feature>(url, data);
  }
}
