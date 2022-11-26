import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarCreateFormDialogComponent } from './car-create-form-dialog.component';

describe('CarCreateFormDialogComponent', () => {
  let component: CarCreateFormDialogComponent;
  let fixture: ComponentFixture<CarCreateFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarCreateFormDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarCreateFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
