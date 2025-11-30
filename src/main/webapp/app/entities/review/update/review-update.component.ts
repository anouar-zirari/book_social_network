import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IBooktesting } from 'app/entities/booktesting/booktesting.model';
import { BooktestingService } from 'app/entities/booktesting/service/booktesting.service';
import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/service/user.service';
import { ReviewService } from '../service/review.service';
import { IReview } from '../review.model';
import { ReviewFormGroup, ReviewFormService } from './review-form.service';

@Component({
  selector: 'jhi-review-update',
  templateUrl: './review-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ReviewUpdateComponent implements OnInit {
  isSaving = false;
  review: IReview | null = null;

  booktestingsSharedCollection: IBooktesting[] = [];
  usersSharedCollection: IUser[] = [];

  protected reviewService = inject(ReviewService);
  protected reviewFormService = inject(ReviewFormService);
  protected booktestingService = inject(BooktestingService);
  protected userService = inject(UserService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ReviewFormGroup = this.reviewFormService.createReviewFormGroup();

  compareBooktesting = (o1: IBooktesting | null, o2: IBooktesting | null): boolean => this.booktestingService.compareBooktesting(o1, o2);

  compareUser = (o1: IUser | null, o2: IUser | null): boolean => this.userService.compareUser(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ review }) => {
      this.review = review;
      if (review) {
        this.updateForm(review);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const review = this.reviewFormService.getReview(this.editForm);
    if (review.id !== null) {
      this.subscribeToSaveResponse(this.reviewService.update(review));
    } else {
      this.subscribeToSaveResponse(this.reviewService.create(review));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReview>>): void {
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

  protected updateForm(review: IReview): void {
    this.review = review;
    this.reviewFormService.resetForm(this.editForm, review);

    this.booktestingsSharedCollection = this.booktestingService.addBooktestingToCollectionIfMissing<IBooktesting>(
      this.booktestingsSharedCollection,
      review.manytoone,
    );
    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing<IUser>(this.usersSharedCollection, review.user);
  }

  protected loadRelationshipsOptions(): void {
    this.booktestingService
      .query()
      .pipe(map((res: HttpResponse<IBooktesting[]>) => res.body ?? []))
      .pipe(
        map((booktestings: IBooktesting[]) =>
          this.booktestingService.addBooktestingToCollectionIfMissing<IBooktesting>(booktestings, this.review?.manytoone),
        ),
      )
      .subscribe((booktestings: IBooktesting[]) => (this.booktestingsSharedCollection = booktestings));

    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing<IUser>(users, this.review?.user)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));
  }
}
