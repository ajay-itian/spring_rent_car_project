import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import * as QRCode from 'qrcode';

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
  booking: any;
  selectedBookingId: string | null = null; // Track the selected booking for QR code

  constructor(private service: CustomerService) {
    this.getBookings();
  }

  getBookings() {
    this.isSpinning = true;
    this.service.getBookingByUserId().subscribe(
      (res) => {
        console.log("response data");
        console.log(res);
        this.isSpinning = false;
        this.bookedCars = res;
      },
      (error) => {
        this.isSpinning = false;
        console.error('Error fetching bookings:', error);
      }
    );
  }

  fetchDataAndGenerateQR(booking: any) {
    this.isLoading = true;
    this.qrData = JSON.stringify(booking);
    this.selectedBookingId = booking.id; // Set the selected booking ID
    this.generateQRCodeHTML('qrcode-' + booking.id, this.qrData, booking.bookCarStatus);
    this.isLoading = false;
  }

  generateQRCodeHTML(elementId: string, data: string, status: string) {
    QRCode.toDataURL(data, (err, url) => {
      if (err) {
        console.error('Error generating QR code:', err);
        return;
      }
      const qrCodeElement = document.getElementById(elementId);
      if (qrCodeElement) {
        const borderColor = status === 'APPROVED' ? 'green' : 'red';
        qrCodeElement.innerHTML = `
          <div style="
            background-color: #f0f0f0;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
          ">
            <img src="${url}" alt="QR Code" style="
              border: 5px solid ${borderColor};
              border-radius: 5px;
            ">
          </div>
        `;
      }
    });
  }

  generateQRCodeDownload(booking: any) {
    QRCode.toDataURL(JSON.stringify(booking), (err, url) => {
      if (err) {
        console.error('Error generating QR code for download:', err);
        return;
      }

      const a = document.createElement('a');
      a.href = url;
      a.download = `QR_Code_${booking.id}.png`;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
    });
  }
}
