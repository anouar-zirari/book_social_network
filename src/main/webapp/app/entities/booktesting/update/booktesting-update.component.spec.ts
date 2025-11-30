import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { BooktestingService } from '../service/booktesting.service';
import { IBooktesting } from '../booktesting.model';
import { BooktestingFormService } from './booktesting-form.service';

import { BooktestingUpdateComponent } from './booktesting-update.component';

describe('Booktesting Management Update Component', () => {
  let comp: BooktestingUpdateComponent;
  let fixture: ComponentFixture<BooktestingUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let booktestingFormService: BooktestingFormService;
  let booktestingService: BooktestingService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [BooktestingUpdateComponent],
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
      .overrideTemplate(BooktestingUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BooktestingUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    booktestingFormService = TestBed.inject(BooktestingFormService);
    booktestingService = TestBed.inject(BooktestingService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('should update editForm', () => {
      const booktesting: IBooktesting = { id: 2470 };

      activatedRoute.data = of({ booktesting });
      comp.ngOnInit();

      expect(comp.booktesting).toEqual(booktesting);
    });
  });

  describe('save', () => {
    it('should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBooktesting>>();
      const booktesting = { id: 22632 };
      jest.spyOn(booktestingFormService, 'getBooktesting').mockReturnValue(booktesting);
      jest.spyOn(booktestingService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ booktesting });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: booktesting }));
      saveSubject.complete();

      // THEN
      expect(booktestingFormService.getBooktesting).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(booktestingService.update).toHaveBeenCalledWith(expect.objectContaining(booktesting));
      expect(comp.isSaving).toEqual(false);
    });

    it('should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBooktesting>>();
      const booktesting = { id: 22632 };
      jest.spyOn(booktestingFormService, 'getBooktesting').mockReturnValue({ id: null });
      jest.spyOn(booktestingService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ booktesting: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: booktesting }));
      saveSubject.complete();

      // THEN
      expect(booktestingFormService.getBooktesting).toHaveBeenCalled();
      expect(booktestingService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBooktesting>>();
      const booktesting = { id: 22632 };
      jest.spyOn(booktestingService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ booktesting });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(booktestingService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
