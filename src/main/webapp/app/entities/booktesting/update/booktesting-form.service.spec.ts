import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../booktesting.test-samples';

import { BooktestingFormService } from './booktesting-form.service';

describe('Booktesting Form Service', () => {
  let service: BooktestingFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BooktestingFormService);
  });

  describe('Service methods', () => {
    describe('createBooktestingFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBooktestingFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            lastname: expect.any(Object),
            email: expect.any(Object),
          }),
        );
      });

      it('passing IBooktesting should create a new form with FormGroup', () => {
        const formGroup = service.createBooktestingFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            lastname: expect.any(Object),
            email: expect.any(Object),
          }),
        );
      });
    });

    describe('getBooktesting', () => {
      it('should return NewBooktesting for default Booktesting initial value', () => {
        const formGroup = service.createBooktestingFormGroup(sampleWithNewData);

        const booktesting = service.getBooktesting(formGroup) as any;

        expect(booktesting).toMatchObject(sampleWithNewData);
      });

      it('should return NewBooktesting for empty Booktesting initial value', () => {
        const formGroup = service.createBooktestingFormGroup();

        const booktesting = service.getBooktesting(formGroup) as any;

        expect(booktesting).toMatchObject({});
      });

      it('should return IBooktesting', () => {
        const formGroup = service.createBooktestingFormGroup(sampleWithRequiredData);

        const booktesting = service.getBooktesting(formGroup) as any;

        expect(booktesting).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBooktesting should not enable id FormControl', () => {
        const formGroup = service.createBooktestingFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBooktesting should disable id FormControl', () => {
        const formGroup = service.createBooktestingFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
