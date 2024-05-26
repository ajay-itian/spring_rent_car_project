import { NgModule } from '@angular/core';


import { NzModule } from 'src/app/NzModule';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { NzNotificationModule } from 'ng-zorro-antd/notification';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { PostCarComponent } from './components/post-car/post-car.component';
import { UpdateCarComponent } from './components/update-car/update-car.component';
import { GetBookingsComponent } from './components/get-bookings/get-bookings.component';
    


    



@NgModule({
  declarations: [
    AdminDashboardComponent,
    PostCarComponent,
    UpdateCarComponent,
    GetBookingsComponent
  ],
  imports: [
    NzModule,
    CommonModule,
    AdminRoutingModule ,
    NzNotificationModule
  ]
})
export class AdminModule { }
