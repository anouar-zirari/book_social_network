import dayjs from 'dayjs/esm';

import { IBook, NewBook } from './book.model';

export const sampleWithRequiredData: IBook = {
  id: 3991,
};

export const sampleWithPartialData: IBook = {
  id: 2220,
  price: 24764.11,
  isbn: 'gym until matter',
};

export const sampleWithFullData: IBook = {
  id: 8637,
  author: 'sniff',
  aboutAuthor: 'however quicker',
  title: 'reasoning repeatedly instead',
  category: 'blushing shipper gosh',
  publishDate: dayjs('2025-11-29'),
  price: 10093.81,
  isbn: 'maroon',
  authorImage: '../fake-data/blob/hipster.png',
  authorImageContentType: 'unknown',
  coverImage: '../fake-data/blob/hipster.png',
  coverImageContentType: 'unknown',
};

export const sampleWithNewData: NewBook = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
