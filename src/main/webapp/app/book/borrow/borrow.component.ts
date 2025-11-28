import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import SharedModule from 'app/shared/shared.module';

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

  borrowBook() {}
}
