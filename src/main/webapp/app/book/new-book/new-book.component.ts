import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'jhi-new-book',
  imports: [ReactiveFormsModule],
  templateUrl: './new-book.component.html',
  styleUrl: './new-book.component.scss',
})
export class NewBookComponent {
  bookCreation = new FormGroup({
    author: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    title: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    category: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    publishDate: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    price: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    isbn: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    coverImage: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    pages: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
    language: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
  });

  create() {}
}
