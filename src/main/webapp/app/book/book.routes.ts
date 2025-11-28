import { Routes } from '@angular/router';
import { NewBookComponent } from './new-book/new-book.component';
import routes from 'app/app.routes';
import { BookListComponent } from './book-list/book-list.component';
import { BookDetailsComponent } from './book-details/book-details.component';
import { BorrowComponent } from './borrow/borrow.component';

export const booksRoutes: Routes = [
  {
    path: 'new-book',
    component: NewBookComponent,
  },
  {
    path: 'book-list',
    component: BookListComponent,
  },
  {
    path: 'book-details',
    component: BookDetailsComponent,
  },
  {
    path: 'borrow',
    component: BorrowComponent,
  },
];

export default booksRoutes;
