import { Component } from '@angular/core';
import SharedModule from 'app/shared/shared.module';
import { ɵɵRouterLink } from '@angular/router/testing';

@Component({
  selector: 'jhi-book-list',
  imports: [SharedModule, ɵɵRouterLink],
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.scss',
})
export class BookListComponent {
  rating = 8;

  books = [
    {
      title: 'Book A',
      author: 'John Doe',
      coverImage: '../../../content/images/book-cover2.png',
    },
    {
      title: 'Book B',
      author: 'Alice Smith',
      coverImage: '../../../content/images/book-cover.png',
    },
    {
      title: 'Book A',
      author: 'John Doe',
      coverImage: '../../../content/images/book-cover2.png',
    },
    {
      title: 'Book B',
      author: 'Alice Smith',
      coverImage: '../../../content/images/book-cover.png',
    },
    {
      title: 'Book A',
      author: 'John Doe',
      coverImage: '../../../content/images/book-cover2.png',
    },
    {
      title: 'Book B',
      author: 'Alice Smith',
      coverImage: '../../../content/images/book-cover.png',
    },
    // ... add more or load from backend
  ];
}
