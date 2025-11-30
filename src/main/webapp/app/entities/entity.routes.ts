import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'Authorities' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'booktesting',
    data: { pageTitle: 'Booktestings' },
    loadChildren: () => import('./booktesting/booktesting.routes'),
  },
  {
    path: 'review',
    data: { pageTitle: 'Reviews' },
    loadChildren: () => import('./review/review.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
