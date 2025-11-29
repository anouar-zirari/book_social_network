import dayjs from 'dayjs/esm';

export interface IBook {
  id: number;
  author?: string | null;
  aboutAuthor?: string | null;
  title?: string | null;
  category?: string | null;
  publishDate?: dayjs.Dayjs | null;
  price?: number | null;
  isbn?: string | null;
  authorImage?: string | null;
  authorImageContentType?: string | null;
  coverImage?: string | null;
  coverImageContentType?: string | null;
}

export type NewBook = Omit<IBook, 'id'> & { id: null };
