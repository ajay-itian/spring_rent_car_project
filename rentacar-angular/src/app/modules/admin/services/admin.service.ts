import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth/services/storage.service';

const BASIC_URL = ["http://localhost:8081"]

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }


  postCar(carDto:any)
  {
    return this.http.post(BASIC_URL+"/api/admin/car",carDto,{
      headers:this.createAuthorizationHeader()
    })

  }

    getAllCars():Observable<any>
    {
      return this.http.get(BASIC_URL +"/api/admin/cars",{
        headers:this.createAuthorizationHeader()
      })
    }

    deleteCars(carId:number):Observable<any>
    {
      return this.http.delete(BASIC_URL +"/api/admin/car/" +carId,{
        headers:this.createAuthorizationHeader()
      })
    }


 getCarById(carId:number):Observable<any>
    {
      return this.http.get(BASIC_URL +"/api/admin/car/" +carId,{
        headers:this.createAuthorizationHeader()
      })
    }


    getBookings():Observable<any>
    {
      return this.http.get(BASIC_URL +"/api/admin/car/bookings",{
        headers:this.createAuthorizationHeader()
      })
    }

    changestatus(id:number,status:string):Observable<any> {
      console.log(id,status);
      return this.http.get(`${BASIC_URL}/api/admin/car/bookings/${id}/${status}`,{
          headers:this.createAuthorizationHeader()
      })
  }
  


    updateCar(carId:number,carDto:any)
    {
      return this.http.put(BASIC_URL+"/api/admin/car/"+carId,carDto,{
        headers:this.createAuthorizationHeader()
      })
  
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
