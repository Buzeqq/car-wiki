import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarDetailDeleteDialogComponent } from './car-detail-delete-dialog.component';

describe('CarDetailDeleteDialogComponent', () => {
  let component: CarDetailDeleteDialogComponent;
  let fixture: ComponentFixture<CarDetailDeleteDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarDetailDeleteDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarDetailDeleteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
