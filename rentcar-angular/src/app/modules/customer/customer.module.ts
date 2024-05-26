import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerDashboardComponent } from './component/customer-dashboard/customer-dashboard.component';
import { NzModule } from 'src/app/NzModule';
import { BookCarComponent } from './component/book-car/book-car.component';
import { GetBookingsComponent } from './component/get-bookings/get-bookings.component';
import { QRCodeModule } from 'angularx-qrcode';


@NgModule({
  declarations: [
    CustomerDashboardComponent,
    BookCarComponent,
    GetBookingsComponent
    
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    NzModule,
    QRCodeModule
  ]
})
export class CustomerModule { }
