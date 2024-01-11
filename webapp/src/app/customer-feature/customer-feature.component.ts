import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs';
import { DataService } from '../service/data.service';
import { Customer } from '../model/customer.model';
import { CustomerFeature, CustomerFeatureRequest, CustomerFeatureResponse, Feature } from '../model/feature.model';

@Component({
  selector: 'app-customer-feature',
  standalone: true,
  imports: [FormsModule, CommonModule],
  providers: [HttpClient],
  templateUrl: './customer-feature.component.html',
  styleUrl: './customer-feature.component.scss',
})
export class CustomerFeatureComponent {
  private subscriptions: Subscription[] = [];
  private customers: Customer[] = [];
  private features: Feature[] = [];
  private customerFeatures: CustomerFeature[] = [];

  constructor(private dataService: DataService) {}

  selectedDisplayName: string = '';
  selectedCustomerId: string = ''

  ngOnInit(): void {
    this.fetchData();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }

  private fetchData() {
    this.subscriptions.push(
      this.dataService.getCustomers().subscribe(data => {
        this.customers = data;
      })
    );
    this.subscriptions.push(
      this.dataService.getFeatures().subscribe(data => {
        this.features = data;
      })
    );
  }

  queryFeature() {
    const req : CustomerFeatureRequest = {
      customerId: parseInt(this.selectedCustomerId),
      features: [
        { name: this.selectedDisplayName }
      ]
    }

    this.subscriptions.push(
      this.dataService.getCustomerFeatures(req).subscribe({
        next: (data:
          CustomerFeatureResponse) => {
          console.log('data is ', data);
          this.customerFeatures = data.features;
        }
      })
    );
  }

  public getFeatures() {
    return this.features;
  }

  public getCustomers() {
    return this.customers;
  }

  getCustomerFeatures(): CustomerFeature[] {
    return this.customerFeatures;
  }
}
