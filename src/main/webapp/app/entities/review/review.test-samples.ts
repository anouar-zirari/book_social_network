import { IReview, NewReview } from './review.model';

export const sampleWithRequiredData: IReview = {
  id: 6640,
};

export const sampleWithPartialData: IReview = {
  id: 12934,
  rating: 'eyeglasses',
};

export const sampleWithFullData: IReview = {
  id: 30916,
  opinion: 'cornet commodity keenly',
  rating: 'aw',
};

export const sampleWithNewData: NewReview = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
