import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IBook, NewBook } from '../book.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBook for edit and NewBookFormGroupInput for create.
 */
type BookFormGroupInput = IBook | PartialWithRequiredKeyOf<NewBook>;

type BookFormDefaults = Pick<NewBook, 'id'>;

type BookFormGroupContent = {
  id: FormControl<IBook['id'] | NewBook['id']>;
  author: FormControl<IBook['author']>;
  aboutAuthor: FormControl<IBook['aboutAuthor']>;
  title: FormControl<IBook['title']>;
  category: FormControl<IBook['category']>;
  publishDate: FormControl<IBook['publishDate']>;
  price: FormControl<IBook['price']>;
  isbn: FormControl<IBook['isbn']>;
  authorImage: FormControl<IBook['authorImage']>;
  authorImageContentType: FormControl<IBook['authorImageContentType']>;
  coverImage: FormControl<IBook['coverImage']>;
  coverImageContentType: FormControl<IBook['coverImageContentType']>;
};

export type BookFormGroup = FormGroup<BookFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BookFormService {
  createBookFormGroup(book: BookFormGroupInput = { id: null }): BookFormGroup {
    const bookRawValue = {
      ...this.getFormDefaults(),
      ...book,
    };
    return new FormGroup<BookFormGroupContent>({
      id: new FormControl(
        { value: bookRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      author: new FormControl(bookRawValue.author),
      aboutAuthor: new FormControl(bookRawValue.aboutAuthor),
      title: new FormControl(bookRawValue.title),
      category: new FormControl(bookRawValue.category),
      publishDate: new FormControl(bookRawValue.publishDate),
      price: new FormControl(bookRawValue.price),
      isbn: new FormControl(bookRawValue.isbn),
      authorImage: new FormControl(bookRawValue.authorImage),
      authorImageContentType: new FormControl(bookRawValue.authorImageContentType),
      coverImage: new FormControl(bookRawValue.coverImage),
      coverImageContentType: new FormControl(bookRawValue.coverImageContentType),
    });
  }

  getBook(form: BookFormGroup): IBook | NewBook {
    return form.getRawValue() as IBook | NewBook;
  }

  resetForm(form: BookFormGroup, book: BookFormGroupInput): void {
    const bookRawValue = { ...this.getFormDefaults(), ...book };
    form.reset(
      {
        ...bookRawValue,
        id: { value: bookRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): BookFormDefaults {
    return {
      id: null,
    };
  }
}
