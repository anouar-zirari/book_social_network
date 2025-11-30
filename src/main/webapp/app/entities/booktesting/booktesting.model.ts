export interface IBooktesting {
  id: number;
  name?: string | null;
  lastname?: string | null;
  email?: string | null;
}

export type NewBooktesting = Omit<IBooktesting, 'id'> & { id: null };
