import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { BooktestingDetailComponent } from './booktesting-detail.component';

describe('Booktesting Management Detail Component', () => {
  let comp: BooktestingDetailComponent;
  let fixture: ComponentFixture<BooktestingDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BooktestingDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./booktesting-detail.component').then(m => m.BooktestingDetailComponent),
              resolve: { booktesting: () => of({ id: 22632 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(BooktestingDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BooktestingDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('should load booktesting on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', BooktestingDetailComponent);

      // THEN
      expect(instance.booktesting()).toEqual(expect.objectContaining({ id: 22632 }));
    });
  });

  describe('PreviousState', () => {
    it('should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
