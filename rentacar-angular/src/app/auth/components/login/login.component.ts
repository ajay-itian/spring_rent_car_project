import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { StorageService } from '../../services/storage.service';
import {NzMessageService} from 'ng-zorro-antd/message';
import {  Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent  {

  isSpinning : boolean = false;
  loginform!:FormGroup;

  constructor(private fb:FormBuilder,
    private authService:AuthService,
    private message:NzMessageService,
  private router:Router) {}
  ngOnInit()
  {
    this.loginform=this.fb.group({
      email:[null,[Validators.required,Validators.email]],
      password:[null,[Validators.required]]
    })
  }


  login()
  {
    console.log(this.loginform.value);
    this.authService.login(this.loginform.value).subscribe((res)=>{

      console.log(res);
      console.log(res.userid);

      if(res.userid !=null)
        {
          const user = 
          {
            id:res.userid,
            role:res.userRole
          }
          console.log("User before saving:", user);

          StorageService.saveToken(res.jwt);
          StorageService.saveUser(user);

          console.log("User after saving:", StorageService.getUser());
          if(StorageService.isAdminLoggedIn())
            {
              this.router.navigateByUrl("/admin/dashboard");
            }
          else
          {
            this.router.navigateByUrl("/customer/dashboard");

          }

        }

        else
        {
            this.message.error("Bad Credentials " ,{nzDuration:5000});
        }
    })
    
  }
}
