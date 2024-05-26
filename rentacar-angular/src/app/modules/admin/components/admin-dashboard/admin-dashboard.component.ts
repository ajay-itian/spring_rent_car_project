import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent  {


  cars:any=[];
  constructor(private adminService:AdminService,private message:NzMessageService) {
    this.getAllCars();
   }

   getAllCars() {
    this.adminService.getAllCars().subscribe((res) => {
      console.log(res);
  
      // Assuming res is an array of objects with processedImg and returnedImage properties
      res.forEach((element: { processedImg: string; returnedImage: string; }) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
        this.cars.push(element);
      });
    }, (error) => {
      console.error('Error fetching cars:', error);
    });
  }



  deleteCar(id: number) 
{
  this.cars=[];
  console.log(id);
  this.adminService.deleteCars(id).subscribe((res)=>{
    this.message.success("Car deleted successfully",{nzDuration:5000});

    this.getAllCars();
  })
}
  


}
