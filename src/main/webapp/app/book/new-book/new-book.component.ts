import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgbNavModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-new-book',
  imports: [ReactiveFormsModule, NgbNavModule],
  templateUrl: './new-book.component.html',
  styleUrl: './new-book.component.scss',
})
export class NewBookComponent {
  active = 1;
  bookCreation = new FormGroup({
    author: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    authorDescription: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    title: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    category: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    publishDate: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    price: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    isbn: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    pages: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    language: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
  });

  create() {}
}
