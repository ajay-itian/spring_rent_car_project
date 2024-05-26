import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-customer-dashboard',
  templateUrl: './customer-dashboard.component.html',
  styleUrls: ['./customer-dashboard.component.scss']
})
export class CustomerDashboardComponent {

  cars:any=[];
  constructor(private customerservice:CustomerService,private message:NzMessageService) {
    this.getAllCars();
   }

   getAllCars() {
    this.customerservice.getAllCars().subscribe((res) => {
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
}
