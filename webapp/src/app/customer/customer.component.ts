import { Component, OnDestroy, OnInit } from '@angular/core';
import { DataService } from '../service/data.service';
import { Subscription } from 'rxjs';
import { AddCustomer, Customer } from '../model/customer.model';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  providers: [HttpClient],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.scss',
})
export class CustomerComponent implements OnInit, OnDestroy {
  private customers: Customer[] = [];
  private subscriptions: Subscription[] = [];

  customerForm: FormGroup;

  constructor(
    private dataService: DataService,
    private formBuilder: FormBuilder
  ) {
    this.customerForm = formBuilder.group({
      name: ['', [Validators.required, Validators.maxLength(250)]]
    });
  }

  ngOnInit(): void {
    this.fetchData();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((s) => s.unsubscribe());
  }

  private fetchData() {
    console.log('fetch data called');
    this.subscriptions.push(
      this.dataService.getCustomers().subscribe((data) => {
        this.customers = data;
      })
    );
  }

  public getCustomers() {
    return this.customers;
  }

  addCustomer() {
    if (this.customerForm.valid) {
      const name : string = this.customerForm.get('name')?.value;
      const add: AddCustomer = {
        name: name
      };
      this.subscriptions.push(this.dataService.createCustomer(add)
        .subscribe({
          next: (customer: Customer) => {
            console.log('added customer', customer);
            this.fetchData();
          }
        }));
    }
  }
}
