import { Component } from '@angular/core';
import { NgbNavModule } from '@ng-bootstrap/ng-bootstrap';
import SharedModule from 'app/shared/shared.module';

@Component({
  selector: 'jhi-book-details',
  imports: [SharedModule, NgbNavModule],
  templateUrl: './book-details.component.html',
  styleUrl: './book-details.component.scss',
})
export class BookDetailsComponent {
  active = 1;
  rating = 8;
}
