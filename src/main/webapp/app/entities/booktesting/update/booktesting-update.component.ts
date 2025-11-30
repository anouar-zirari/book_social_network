import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IBooktesting } from '../booktesting.model';
import { BooktestingService } from '../service/booktesting.service';
import { BooktestingFormGroup, BooktestingFormService } from './booktesting-form.service';

@Component({
  selector: 'jhi-booktesting-update',
  templateUrl: './booktesting-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class BooktestingUpdateComponent implements OnInit {
  isSaving = false;
  booktesting: IBooktesting | null = null;

  protected booktestingService = inject(BooktestingService);
  protected booktestingFormService = inject(BooktestingFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: BooktestingFormGroup = this.booktestingFormService.createBooktestingFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ booktesting }) => {
      this.booktesting = booktesting;
      if (booktesting) {
        this.updateForm(booktesting);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const booktesting = this.booktestingFormService.getBooktesting(this.editForm);
    if (booktesting.id !== null) {
      this.subscribeToSaveResponse(this.booktestingService.update(booktesting));
    } else {
      this.subscribeToSaveResponse(this.booktestingService.create(booktesting));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBooktesting>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(booktesting: IBooktesting): void {
    this.booktesting = booktesting;
    this.booktestingFormService.resetForm(this.editForm, booktesting);
  }
}
