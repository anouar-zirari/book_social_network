import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBooktesting } from '../booktesting.model';
import { BooktestingService } from '../service/booktesting.service';

const booktestingResolve = (route: ActivatedRouteSnapshot): Observable<null | IBooktesting> => {
  const id = route.params.id;
  if (id) {
    return inject(BooktestingService)
      .find(id)
      .pipe(
        mergeMap((booktesting: HttpResponse<IBooktesting>) => {
          if (booktesting.body) {
            return of(booktesting.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default booktestingResolve;
