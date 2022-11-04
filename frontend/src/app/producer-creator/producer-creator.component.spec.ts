import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProducerCreatorComponent } from './producer-creator.component';

describe('ProducerCreatorComponent', () => {
  let component: ProducerCreatorComponent;
  let fixture: ComponentFixture<ProducerCreatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProducerCreatorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProducerCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
