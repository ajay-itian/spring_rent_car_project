import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from 'src/app/auth/services/storage.service';

@Component({
  selector: 'app-book-car',
  templateUrl: './book-car.component.html',
  styleUrls: ['./book-car.component.scss']
})
export class BookCarComponent {

  car:any=[];
  carId:number=this.activated.snapshot.params["id"];
  bookACarForm!:FormGroup;
dateFormat!: "yyyy-mm-dd";
isSpinning=false;
  
  constructor(private customerservice:CustomerService,
    private message:NzMessageService,
    private activated:ActivatedRoute,
    private fb:FormBuilder) 
    {
    
      this.bookACarForm=this.fb.group({
      fromDate:[null,Validators.required],
      toDate:[null,Validators.required]

    })
    this.getCarById();
   }

   getCarById()
   {
    console.log("getCarById()");
     this.customerservice.getCarById(this.carId).subscribe((res)=>{
      console.log(res);
      res.processedImg='data:image/jpeg;base64,'+res.returnedImage;
      this.car=res;
     })
   }


   bookCar(formData:any)
   {
    this.isSpinning=true;
      let obj={

        fromDate:formData.fromDate,
        toDate:formData.toDate,
        userId:StorageService.getUserId()
      }
      console.log(obj);
      console.log("car id"+this.carId);

      this.customerservice.bookACar(this.carId,obj)
      .subscribe((res)=>{
        console.log(res);
        this.isSpinning=false;
        
        this.message.success("Car booked Successfully",{nzDuration:5000});
       
       
      },error=>{
        this.message.success("Car booked Failed",{nzDuration:5000});
      })
   }
}
