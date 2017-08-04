import { WORKOUT } from './../common/workout.testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutCreateComponent } from './workout-create.component';

describe('WorkoutCreateComponent', () => {
  let component: WorkoutCreateComponent;
  let fixture: ComponentFixture<WorkoutCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [WorkoutCreateComponent],
      imports: [
        RouterTestingModule,
        FormsModule
      ]
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

  it('should add new day to workout', () => {
    component.workout = WORKOUT;
    expect(component.workout.days.length).toEqual(0);
    fixture.nativeElement.querySelector('#addWorkoutBtn').click();
    expect(component.workout.days.length).toEqual(1);
  });

});
