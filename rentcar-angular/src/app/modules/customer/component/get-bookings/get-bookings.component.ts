import { Component } from '@angular/core';
import { CustomerService } from '../../service/customer.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-get-bookings',
  templateUrl: './get-bookings.component.html',
  styleUrls: ['./get-bookings.component.scss']
})
export class GetBookingsComponent {
  isSpinning = false;
  bookedCars: any;
  isLoading = false;
  qrData: any;

  constructor(private service: CustomerService) {
    this.getBookings();
  }

  getBookings() {
    this.isSpinning = true;
    this.service.getBookingByUserId().subscribe((res) => {
      this.isSpinning = false;
      console.log(res);
      this.bookedCars = res;
    });
  }

  fetchDataAndGenerateQR() {
    this.isLoading = true;
    this.service.getBookingByUserId().subscribe((res) => {
      this.qrData = JSON.stringify(res); // Assuming response is an object
      this.isLoading = false;
    });
  }

  downloadQRCode() {
    if (this.qrData) {
      // Convert QR data to Blob
      const qrCodeBlob = new Blob([this.qrData], { type: 'application/json' });
      // Download the Blob as a file
      saveAs(qrCodeBlob, 'qrcode.json');
    } else {
      console.error('No QR code data available.');
    }
  }
}
