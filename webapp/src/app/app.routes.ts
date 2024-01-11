import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CustomerComponent } from './customer/customer.component';
import { FeatureComponent } from './feature/feature.component';
import { CustomerFeatureComponent } from './customer-feature/customer-feature.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'customer', component: CustomerComponent },
  { path: 'feature', component: FeatureComponent },
  { path: 'customerFeature', component: CustomerFeatureComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
];
