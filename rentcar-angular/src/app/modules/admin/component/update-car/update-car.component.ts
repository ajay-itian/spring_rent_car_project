import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification'; // Correct import

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrls: ['./update-car.component.scss']
})
export class UpdateCarComponent {

  carId: number = this.activatedRoute.snapshot.params["id"];
  updateForm!: FormGroup;
  isSpinning = false;
  selectedFile!: File;
  imagePreview: string | ArrayBuffer | null = null;
  imageChanged = false;
  existingImage: string | null = null;

  listOfBrands = ["BMW", "AUDI", "FERRARI", "TESLA", "VOLVO", "HONDA", "FORD", "NISSAN", "HYUNDAI", "LEXUS", "KIA"];
  listOfType = ["Petrol", "Hybrid", "Electric", "CNG", "Diesel"];
  listOfColor = ["Red", "White", "Blue", "Silver", "Orange", "Black", "Blue", "Grey"];
  listOfTransmission = ["Manual", "Automatic"];

  constructor(private service: AdminService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private notification: NzNotificationService,
    private router: Router) { }

  ngOnInit(): void {
    this.updateForm = this.fb.group({
      name: [null, Validators.required],
      brand: [null, Validators.required],
      type: [null, Validators.required],
      transmission: [null, Validators.required],
      color: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
      year: [null, Validators.required] // Ensure year is bound to a Date object
    });
    this.getCarById();
  }

  getCarById() {
    this.service.getCarById(this.carId).subscribe((res) => {
      console.log(res);

      const carDto = res;
      carDto.year = new Date(carDto.year);
      this.existingImage = 'data:image/jpeg;base64,' + res.returnedImage;
      this.updateForm.patchValue(carDto);
    });
  }

  updateCar() {
    this.isSpinning = true;
    const formData = new FormData();
    if (this.imageChanged && this.selectedFile) {
      formData.append('image', this.selectedFile);
    }
  
    formData.append('brand', this.updateForm.get('brand')?.value);
    formData.append('name', this.updateForm.get('name')?.value);
    formData.append('type', this.updateForm.get('type')?.value);
    formData.append('color', this.updateForm.get('color')?.value);
    formData.append('year', this.updateForm.get('year')?.value);
    formData.append('transmission', this.updateForm.get('transmission')?.value);
    formData.append('description', this.updateForm.get('description')?.value);
    formData.append('price', this.updateForm.get('price')?.value);
    this.service.updateCar(this.carId,formData).subscribe((res)=>{
      console.log(res);
      this.notification.success("Success", "Car Updated successfully", { nzDuration: 5000 });
      this.router.navigateByUrl("/admin/dashboard");
    },error=>{
      this.notification.error("Error", "Failed to update car", { nzDuration: 5000 });
      this.isSpinning = false;
    });
  }
  

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.imageChanged = true;
    this.existingImage = null;
    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };

    reader.readAsDataURL(this.selectedFile);
  }
}
