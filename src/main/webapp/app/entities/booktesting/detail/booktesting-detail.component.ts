import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { IBooktesting } from '../booktesting.model';

@Component({
  selector: 'jhi-booktesting-detail',
  templateUrl: './booktesting-detail.component.html',
  imports: [SharedModule, RouterModule],
})
export class BooktestingDetailComponent {
  booktesting = input<IBooktesting | null>(null);

  previousState(): void {
    window.history.back();
  }
}
