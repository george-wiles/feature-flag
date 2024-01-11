import { Component, OnDestroy, OnInit } from '@angular/core';
import { DataService } from '../service/data.service';
import { Subscription } from 'rxjs';
import { Customer } from '../model/customer.model';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [CommonModule],
  providers: [HttpClient],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.scss'
})
export class CustomerComponent implements OnInit, OnDestroy {
  private customers: Customer[] = [];
  private subscriptions: Subscription[] = [];

  constructor(private dataService: DataService) {}

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
  }

  public getCustomers() {
    return this.customers;
  }

}
