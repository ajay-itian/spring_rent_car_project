import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerDashboardComponent } from './components/customer-dashboard/customer-dashboard.component';
import { NzModule } from 'src/app/NzModule';
import { BookCarComponent } from './components/book-car/book-car.component';
import { GetBookingsComponent } from './components/get-bookings/get-bookings.component';



@NgModule({
  declarations: [
    CustomerDashboardComponent,
    BookCarComponent,
    GetBookingsComponent
    
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    NzModule
  ]
})
export class CustomerModule { }
