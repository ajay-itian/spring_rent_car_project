import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './component/admin-dashboard/admin-dashboard.component';
import { PostCarComponent } from './component/post-car/post-car.component';
import { UpdateCarComponent } from './component/update-car/update-car.component';
import { GetBookingsComponent } from './component/get-bookings/get-bookings.component';

const routes: Routes = [
  { path: 'dashboard', component: AdminDashboardComponent },
  { path: 'car', component: PostCarComponent }, // Route for 'admin/car'
  {path:'car/:id/edit',component:UpdateCarComponent},
  {path:'bookings',component:GetBookingsComponent},

  // Other child routes for the admin module
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
