import { NgModule } from '@angular/core';

import { AdminDashboardComponent } from './component/admin-dashboard/admin-dashboard.component';
import { PostCarComponent } from './component/post-car/post-car.component';
import { NzModule } from 'src/app/NzModule';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { NzNotificationModule } from 'ng-zorro-antd/notification';
import { UpdateCarComponent } from './component/update-car/update-car.component';
import { GetBookingsComponent } from './component/get-bookings/get-bookings.component';
    


    



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
