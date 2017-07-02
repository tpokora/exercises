import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutCreateComponent } from './workout-create.component';

describe('WorkoutCreateComponent', () => {
  let component: WorkoutCreateComponent;
  let fixture: ComponentFixture<WorkoutCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkoutCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
