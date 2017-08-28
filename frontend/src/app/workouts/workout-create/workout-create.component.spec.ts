import { Exercise } from './../../exercises/common/exercise.model';
import { Workout } from './../common/workout.model';
import { ExerciseServiceTests, EXERCISES } from './../../exercises/common/exercise.testing';
import { ExerciseService } from './../../exercises/common/exercise.service';
import { WORKOUTS } from './../common/workout.testing';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { NgbTypeahead, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { WorkoutCreateComponent } from './workout-create.component';

describe('WorkoutCreateComponent', () => {
  let component: WorkoutCreateComponent;
  let fixture: ComponentFixture<WorkoutCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [WorkoutCreateComponent],
      imports: [
        RouterTestingModule,
        FormsModule,
        NgbModule.forRoot()
      ],
      providers: [
        { provide: ExerciseService, useClass: ExerciseServiceTests },
        { provide: NgbTypeahead }
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
    component.workout = WORKOUTS[0];
    expect(component.workout.days.length).toEqual(0);
    fixture.nativeElement.querySelector('#addDayBtn').click();
    expect(component.workout.days.length).toEqual(1);
  });

  it('should add new exercise set to workout day', () => {
    const exercise = EXERCISES[0];
    const sets = 4;
    const reps = 10;
    component.exercises[0] = exercise;
    component.sets[0] = sets;
    component.reps[0] = reps;
    component.workout = WORKOUTS[0];

    fixture.nativeElement.querySelector('#addDayBtn').click();
    expect(component.workout.days.length).toBeGreaterThan(0);
    fixture.detectChanges();

    fixture.nativeElement.querySelector('#addExerciseSetBtn').click();
    const workout = component.workout;
    expect(workout.days[0].exerciseSets.length).toEqual(1);
    expect(workout.days[0].exerciseSets[0].exercise.name).toEqual(exercise.name);
    expect(workout.days[0].exerciseSets[0].sets).toEqual(sets);
    expect(workout.days[0].exerciseSets[0].reps).toEqual(reps);
  });

  it('should remove day from workout', () => {
    component.workout = WORKOUTS[0];
    const daysAmount = component.workout.days.length;
    fixture.nativeElement.querySelector('#addDayBtn').click();
    expect(component.workout.days.length).toEqual(daysAmount + 1);
    fixture.detectChanges();

    fixture.nativeElement.querySelector('button.removeDayBtn').click();
    expect(component.workout.days.length).toEqual(daysAmount);
    let index = 0;
    for (let day of component.workout.days) {
      expect(day.index).toEqual(index);
      index++;
    }
  });

});
