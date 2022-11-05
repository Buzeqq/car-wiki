import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProducerCreateFormDialogComponent } from './producer-create-form-dialog.component';

describe('ProducerCreateFormDialogComponent', () => {
  let component: ProducerCreateFormDialogComponent;
  let fixture: ComponentFixture<ProducerCreateFormDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProducerCreateFormDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProducerCreateFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
