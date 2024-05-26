// customer-routing.module.ts

import { NgModule } from '@angular/core';
import { CustomerDashboardComponent } from './components/customer-dashboard/customer-dashboard.component';
import { BookCarComponent } from './components/book-car/book-car.component';
import { Routes, RouterModule } from '@angular/router';
import { GetBookingsComponent } from './components/get-bookings/get-bookings.component';

const routes: Routes = [
  { path: "dashboard", component: CustomerDashboardComponent },
  { path: "book_car/:id", component:  BookCarComponent}, // Use :id as a placeholder for dynamic car IDs
  {path:"bookings",component:GetBookingsComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
