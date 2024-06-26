import { Component } from '@angular/core';
import { StorageService } from './auth/services/storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'rentcar-angular';

  constructor(private router:Router){}
  isAdminLoggedIn:boolean=StorageService.isAdminLoggedIn();
  isCustomerloggedIn:boolean=StorageService.isCustomerLoggedIn();


  ngOnInit()
  {
    this.router.events.subscribe(event => {
      if(event.constructor.name==="NavigationEnd")
        {
          this.isAdminLoggedIn=StorageService.isAdminLoggedIn();
          this.isCustomerloggedIn=StorageService.isCustomerLoggedIn();
        }
    })
  }

  logout()
  {
    StorageService.logout();
    this.router.navigateByUrl("/login");
  }

}
