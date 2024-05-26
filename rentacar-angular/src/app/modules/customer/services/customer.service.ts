import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth/services/storage.service';

const BASIC_URL=["http://localhost:8081"];
@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient) {}


  getAllCars():Observable<any>
  {
    return this.http.get(BASIC_URL+"/api/customer/cars",{
      headers:this.createAuthorizationHeader()
    })
  }

  
  getCarById(carId:number):Observable<any>
  {
    return this.http.get(BASIC_URL+"/api/customer/car/"+carId,{
      headers:this.createAuthorizationHeader()
    })
  }


  getBookingByUserId():Observable<any>
  {
    console.log(StorageService.getUserId())
    return this.http.get(BASIC_URL+"/api/customer/car/bookings/"+StorageService.getUserId(),{
      headers:this.createAuthorizationHeader()
    })
  }

  bookACar(carId: number, bookcarDto: any): Observable<any> {
    console.log("Customer Service Called bookACar()");
    return this.http.post(BASIC_URL + "/api/customer/car/book/" + carId, bookcarDto, {
      headers: this.createAuthorizationHeader()
    });
  }
  
 


  createAuthorizationHeader():HttpHeaders
  {
    let authHeader:HttpHeaders=new HttpHeaders();

    return authHeader.set(
      'Authorization',
      'Bearer '+StorageService.getToken()
    )
  }
}
