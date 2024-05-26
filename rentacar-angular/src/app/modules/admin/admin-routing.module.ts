import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { PostCarComponent } from './components/post-car/post-car.component';
import { UpdateCarComponent } from './components/update-car/update-car.component';
import { GetBookingsComponent } from './components/get-bookings/get-bookings.component';


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
