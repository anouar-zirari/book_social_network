import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IBooktesting } from '../booktesting.model';
import { BooktestingService } from '../service/booktesting.service';

@Component({
  templateUrl: './booktesting-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class BooktestingDeleteDialogComponent {
  booktesting?: IBooktesting;

  protected booktestingService = inject(BooktestingService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.booktestingService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
