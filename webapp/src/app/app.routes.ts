import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CustomerComponent } from './customer/customer.component';
import { FeatureComponent } from './feature/feature.component';

export const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'customer', component: CustomerComponent },
  { path: 'feature', component: FeatureComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
];
