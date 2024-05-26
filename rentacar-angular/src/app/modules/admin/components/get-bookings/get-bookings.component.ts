import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import {  NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-get-bookings',
  templateUrl: './get-bookings.component.html',
  styleUrls: ['./get-bookings.component.scss']
})
export class GetBookingsComponent {


  isSpinning=false;
  bookedCars:any;


  constructor(private service:AdminService,
    private message:NzMessageService
  ) {
    this.getBookings();
   }


   getBookings()
   {
    this.isSpinning=true;
    this.service.getBookings().subscribe((res)=>{
      this.isSpinning=false;
      console.log(res);
      this.bookedCars=res;
    })
   }


   changeBookingStatus(bookingId:number,status:string) {
    console.log(bookingId,status);
    this.isSpinning=true;

    this.service.changestatus(bookingId,status).subscribe((res)=>{
        this.isSpinning=false;
        this.getBookings();
        this.message.success("status changed successfulyy",{nzDuration:5000})

    },error=>{
      this.message.error("something went wrong",{nzDuration:5000})

    })
    }

}
