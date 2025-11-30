import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IBooktesting, NewBooktesting } from '../booktesting.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBooktesting for edit and NewBooktestingFormGroupInput for create.
 */
type BooktestingFormGroupInput = IBooktesting | PartialWithRequiredKeyOf<NewBooktesting>;

type BooktestingFormDefaults = Pick<NewBooktesting, 'id'>;

type BooktestingFormGroupContent = {
  id: FormControl<IBooktesting['id'] | NewBooktesting['id']>;
  name: FormControl<IBooktesting['name']>;
  lastname: FormControl<IBooktesting['lastname']>;
  email: FormControl<IBooktesting['email']>;
};

export type BooktestingFormGroup = FormGroup<BooktestingFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BooktestingFormService {
  createBooktestingFormGroup(booktesting: BooktestingFormGroupInput = { id: null }): BooktestingFormGroup {
    const booktestingRawValue = {
      ...this.getFormDefaults(),
      ...booktesting,
    };
    return new FormGroup<BooktestingFormGroupContent>({
      id: new FormControl(
        { value: booktestingRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      name: new FormControl(booktestingRawValue.name),
      lastname: new FormControl(booktestingRawValue.lastname),
      email: new FormControl(booktestingRawValue.email, {
        validators: [Validators.required, Validators.pattern('^[a-zA-Z0-9]*$')],
      }),
    });
  }

  getBooktesting(form: BooktestingFormGroup): IBooktesting | NewBooktesting {
    return form.getRawValue() as IBooktesting | NewBooktesting;
  }

  resetForm(form: BooktestingFormGroup, booktesting: BooktestingFormGroupInput): void {
    const booktestingRawValue = { ...this.getFormDefaults(), ...booktesting };
    form.reset(
      {
        ...booktestingRawValue,
        id: { value: booktestingRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): BooktestingFormDefaults {
    return {
      id: null,
    };
  }
}
