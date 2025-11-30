import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IBooktesting } from 'app/entities/booktesting/booktesting.model';
import { BooktestingService } from 'app/entities/booktesting/service/booktesting.service';
import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/service/user.service';
import { IReview } from '../review.model';
import { ReviewService } from '../service/review.service';
import { ReviewFormService } from './review-form.service';

import { ReviewUpdateComponent } from './review-update.component';

describe('Review Management Update Component', () => {
  let comp: ReviewUpdateComponent;
  let fixture: ComponentFixture<ReviewUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let reviewFormService: ReviewFormService;
  let reviewService: ReviewService;
  let booktestingService: BooktestingService;
  let userService: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ReviewUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ReviewUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ReviewUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    reviewFormService = TestBed.inject(ReviewFormService);
    reviewService = TestBed.inject(ReviewService);
    booktestingService = TestBed.inject(BooktestingService);
    userService = TestBed.inject(UserService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should call Booktesting query and add missing value', () => {
      const review: IReview = { id: 8996 };
      const manytoone: IBooktesting = { id: 22632 };
      review.manytoone = manytoone;

      const booktestingCollection: IBooktesting[] = [{ id: 22632 }];
      jest.spyOn(booktestingService, 'query').mockReturnValue(of(new HttpResponse({ body: booktestingCollection })));
      const additionalBooktestings = [manytoone];
      const expectedCollection: IBooktesting[] = [...additionalBooktestings, ...booktestingCollection];
      jest.spyOn(booktestingService, 'addBooktestingToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ review });
      comp.ngOnInit();

      expect(booktestingService.query).toHaveBeenCalled();
      expect(booktestingService.addBooktestingToCollectionIfMissing).toHaveBeenCalledWith(
        booktestingCollection,
        ...additionalBooktestings.map(expect.objectContaining),
      );
      expect(comp.booktestingsSharedCollection).toEqual(expectedCollection);
    });

    it('should call User query and add missing value', () => {
      const review: IReview = { id: 8996 };
      const user: IUser = { id: 3944 };
      review.user = user;

      const userCollection: IUser[] = [{ id: 3944 }];
      jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({ body: userCollection })));
      const additionalUsers = [user];
      const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
      jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ review });
      comp.ngOnInit();

      expect(userService.query).toHaveBeenCalled();
      expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(
        userCollection,
        ...additionalUsers.map(expect.objectContaining),
      );
      expect(comp.usersSharedCollection).toEqual(expectedCollection);
    });

    it('should update editForm', () => {
      const review: IReview = { id: 8996 };
      const manytoone: IBooktesting = { id: 22632 };
      review.manytoone = manytoone;
      const user: IUser = { id: 3944 };
      review.user = user;

      activatedRoute.data = of({ review });
      comp.ngOnInit();

      expect(comp.booktestingsSharedCollection).toContainEqual(manytoone);
      expect(comp.usersSharedCollection).toContainEqual(user);
      expect(comp.review).toEqual(review);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReview>>();
      const review = { id: 21337 };
      jest.spyOn(reviewFormService, 'getReview').mockReturnValue(review);
      jest.spyOn(reviewService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ review });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: review }));
      saveSubject.complete();

      // THEN
      expect(reviewFormService.getReview).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(reviewService.update).toHaveBeenCalledWith(expect.objectContaining(review));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReview>>();
      const review = { id: 21337 };
      jest.spyOn(reviewFormService, 'getReview').mockReturnValue({ id: null });
      jest.spyOn(reviewService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ review: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: review }));
      saveSubject.complete();

      // THEN
      expect(reviewFormService.getReview).toHaveBeenCalled();
      expect(reviewService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IReview>>();
      const review = { id: 21337 };
      jest.spyOn(reviewService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ review });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(reviewService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareBooktesting', () => {
      it('should forward to booktestingService', () => {
        const entity = { id: 22632 };
        const entity2 = { id: 2470 };
        jest.spyOn(booktestingService, 'compareBooktesting');
        comp.compareBooktesting(entity, entity2);
        expect(booktestingService.compareBooktesting).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareUser', () => {
      it('should forward to userService', () => {
        const entity = { id: 3944 };
        const entity2 = { id: 6275 };
        jest.spyOn(userService, 'compareUser');
        comp.compareUser(entity, entity2);
        expect(userService.compareUser).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
