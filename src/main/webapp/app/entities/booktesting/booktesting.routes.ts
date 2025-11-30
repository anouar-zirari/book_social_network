import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import BooktestingResolve from './route/booktesting-routing-resolve.service';

const booktestingRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/booktesting.component').then(m => m.BooktestingComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/booktesting-detail.component').then(m => m.BooktestingDetailComponent),
    resolve: {
      booktesting: BooktestingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/booktesting-update.component').then(m => m.BooktestingUpdateComponent),
    resolve: {
      booktesting: BooktestingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/booktesting-update.component').then(m => m.BooktestingUpdateComponent),
    resolve: {
      booktesting: BooktestingResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default booktestingRoute;
