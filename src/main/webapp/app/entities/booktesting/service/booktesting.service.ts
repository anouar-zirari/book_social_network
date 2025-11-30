import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBooktesting, NewBooktesting } from '../booktesting.model';

export type PartialUpdateBooktesting = Partial<IBooktesting> & Pick<IBooktesting, 'id'>;

export type EntityResponseType = HttpResponse<IBooktesting>;
export type EntityArrayResponseType = HttpResponse<IBooktesting[]>;

@Injectable({ providedIn: 'root' })
export class BooktestingService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/booktestings');

  create(booktesting: NewBooktesting): Observable<EntityResponseType> {
    return this.http.post<IBooktesting>(this.resourceUrl, booktesting, { observe: 'response' });
  }

  update(booktesting: IBooktesting): Observable<EntityResponseType> {
    return this.http.put<IBooktesting>(`${this.resourceUrl}/${this.getBooktestingIdentifier(booktesting)}`, booktesting, {
      observe: 'response',
    });
  }

  partialUpdate(booktesting: PartialUpdateBooktesting): Observable<EntityResponseType> {
    return this.http.patch<IBooktesting>(`${this.resourceUrl}/${this.getBooktestingIdentifier(booktesting)}`, booktesting, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBooktesting>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBooktesting[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBooktestingIdentifier(booktesting: Pick<IBooktesting, 'id'>): number {
    return booktesting.id;
  }

  compareBooktesting(o1: Pick<IBooktesting, 'id'> | null, o2: Pick<IBooktesting, 'id'> | null): boolean {
    return o1 && o2 ? this.getBooktestingIdentifier(o1) === this.getBooktestingIdentifier(o2) : o1 === o2;
  }

  addBooktestingToCollectionIfMissing<Type extends Pick<IBooktesting, 'id'>>(
    booktestingCollection: Type[],
    ...booktestingsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const booktestings: Type[] = booktestingsToCheck.filter(isPresent);
    if (booktestings.length > 0) {
      const booktestingCollectionIdentifiers = booktestingCollection.map(booktestingItem => this.getBooktestingIdentifier(booktestingItem));
      const booktestingsToAdd = booktestings.filter(booktestingItem => {
        const booktestingIdentifier = this.getBooktestingIdentifier(booktestingItem);
        if (booktestingCollectionIdentifiers.includes(booktestingIdentifier)) {
          return false;
        }
        booktestingCollectionIdentifiers.push(booktestingIdentifier);
        return true;
      });
      return [...booktestingsToAdd, ...booktestingCollection];
    }
    return booktestingCollection;
  }
}
