import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProducerDetailDeleteDialogComponent } from './producer-detail-delete-dialog.component';

describe('ProducerDetailDeleteDialogComponent', () => {
  let component: ProducerDetailDeleteDialogComponent;
  let fixture: ComponentFixture<ProducerDetailDeleteDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProducerDetailDeleteDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProducerDetailDeleteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
