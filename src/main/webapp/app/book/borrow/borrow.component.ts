import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import SharedModule from 'app/shared/shared.module';
import jsPDF from 'jspdf';

@Component({
  selector: 'jhi-borrow',
  imports: [SharedModule, ReactiveFormsModule],
  templateUrl: './borrow.component.html',
  styleUrl: './borrow.component.scss',
})
export class BorrowComponent {
  returnDate = '12/12/2024';

  borrowForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    borrowedDays: new FormControl('', [Validators.required, Validators.max(15)]),
  });

  constructor() {}

  generatePdf() {
    const doc = new jsPDF();
    doc.text('Borrow Receipt', 10, 10);
    doc.text(`First Name: ${this.borrowForm.get('firstName')?.value}`, 10, 20);
    doc.text(`Last Name: ${this.borrowForm.get('lastName')?.value}`, 10, 30);
    doc.text(`Return Date: ${this.returnDate}`, 10, 40);
    doc.save('borrow_receipt.pdf');
  }

  borrowBook() {}
}
