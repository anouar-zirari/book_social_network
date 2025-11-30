import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IBooktesting } from '../booktesting.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../booktesting.test-samples';

import { BooktestingService } from './booktesting.service';

const requireRestSample: IBooktesting = {
  ...sampleWithRequiredData,
};

describe('Booktesting Service', () => {
  let service: BooktestingService;
  let httpMock: HttpTestingController;
  let expectedResult: IBooktesting | IBooktesting[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(BooktestingService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Booktesting', () => {
      const booktesting = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(booktesting).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Booktesting', () => {
      const booktesting = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(booktesting).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Booktesting', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Booktesting', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Booktesting', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addBooktestingToCollectionIfMissing', () => {
      it('should add a Booktesting to an empty array', () => {
        const booktesting: IBooktesting = sampleWithRequiredData;
        expectedResult = service.addBooktestingToCollectionIfMissing([], booktesting);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(booktesting);
      });

      it('should not add a Booktesting to an array that contains it', () => {
        const booktesting: IBooktesting = sampleWithRequiredData;
        const booktestingCollection: IBooktesting[] = [
          {
            ...booktesting,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addBooktestingToCollectionIfMissing(booktestingCollection, booktesting);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Booktesting to an array that doesn't contain it", () => {
        const booktesting: IBooktesting = sampleWithRequiredData;
        const booktestingCollection: IBooktesting[] = [sampleWithPartialData];
        expectedResult = service.addBooktestingToCollectionIfMissing(booktestingCollection, booktesting);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(booktesting);
      });

      it('should add only unique Booktesting to an array', () => {
        const booktestingArray: IBooktesting[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const booktestingCollection: IBooktesting[] = [sampleWithRequiredData];
        expectedResult = service.addBooktestingToCollectionIfMissing(booktestingCollection, ...booktestingArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const booktesting: IBooktesting = sampleWithRequiredData;
        const booktesting2: IBooktesting = sampleWithPartialData;
        expectedResult = service.addBooktestingToCollectionIfMissing([], booktesting, booktesting2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(booktesting);
        expect(expectedResult).toContain(booktesting2);
      });

      it('should accept null and undefined values', () => {
        const booktesting: IBooktesting = sampleWithRequiredData;
        expectedResult = service.addBooktestingToCollectionIfMissing([], null, booktesting, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(booktesting);
      });

      it('should return initial array if no Booktesting is added', () => {
        const booktestingCollection: IBooktesting[] = [sampleWithRequiredData];
        expectedResult = service.addBooktestingToCollectionIfMissing(booktestingCollection, undefined, null);
        expect(expectedResult).toEqual(booktestingCollection);
      });
    });

    describe('compareBooktesting', () => {
      it('should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareBooktesting(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('should return false if one entity is null', () => {
        const entity1 = { id: 22632 };
        const entity2 = null;

        const compareResult1 = service.compareBooktesting(entity1, entity2);
        const compareResult2 = service.compareBooktesting(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey differs', () => {
        const entity1 = { id: 22632 };
        const entity2 = { id: 2470 };

        const compareResult1 = service.compareBooktesting(entity1, entity2);
        const compareResult2 = service.compareBooktesting(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('should return false if primaryKey matches', () => {
        const entity1 = { id: 22632 };
        const entity2 = { id: 22632 };

        const compareResult1 = service.compareBooktesting(entity1, entity2);
        const compareResult2 = service.compareBooktesting(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
