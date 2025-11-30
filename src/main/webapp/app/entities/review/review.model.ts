import { IBooktesting } from 'app/entities/booktesting/booktesting.model';
import { IUser } from 'app/entities/user/user.model';

export interface IReview {
  id: number;
  opinion?: string | null;
  rating?: string | null;
  manytoone?: Pick<IBooktesting, 'id'> | null;
  user?: Pick<IUser, 'id'> | null;
}

export type NewReview = Omit<IReview, 'id'> & { id: null };
