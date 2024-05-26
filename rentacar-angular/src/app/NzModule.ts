import { NgModule } from '@angular/core';


// NG ZORRO IMPORT
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzMessageModule } from 'ng-zorro-antd/message'; 
import { NzSelectModule } from 'ng-zorro-antd/select';
import { ReactiveFormsModule } from '@angular/forms';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzTableModule } from 'ng-zorro-antd/table';
// NG ZORRO IMPORT




@NgModule({
 
  exports: [
    
    NzLayoutModule,
    NzFormModule,
    NzMessageModule, // Import NzMessageModule here
    NzInputModule,
    NzSpinModule,
    NzButtonModule,  
    ReactiveFormsModule,
    NzSelectModule,
    NzDatePickerModule ,
    NzTableModule
  ]
 
})
export class NzModule { }
