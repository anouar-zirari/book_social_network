import { IBooktesting, NewBooktesting } from './booktesting.model';

export const sampleWithRequiredData: IBooktesting = {
  id: 24491,
  email: 'U9V6B',
};

export const sampleWithPartialData: IBooktesting = {
  id: 31603,
  email: 'P8',
};

export const sampleWithFullData: IBooktesting = {
  id: 2513,
  name: 'along',
  lastname: 'aboard inasmuch the',
  email: 'E',
};

export const sampleWithNewData: NewBooktesting = {
  email: 'UR',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
