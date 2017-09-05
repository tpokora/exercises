import { ProfileServiceTests } from './../../common/google-auth/common/profile.testing';
import { ProfileService } from './../../common/google-auth/common/profile.service';
import { Day } from './../common/day.model';
import { WorkoutService } from './../common/workout.service';
import { Exercise } from './../../exercises/common/exercise.model';
import { Workout } from './../common/workout.model';
import { ExerciseServiceTests, EXERCISES } from './../../exercises/common/exercise.testing';
import { ExerciseService } from './../../exercises/common/exercise.service';
import { WORKOUTS, WorkoutServiceTest } from './../common/workout.testing';
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
        { provide: ProfileService, useClass: ProfileServiceTests },
        { provide: WorkoutService, useClass: WorkoutServiceTest },
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

  function setComponent() {
    let workout = WORKOUTS[0];
    workout.days = new Array<Day>();
    component.workout = workout;
  }

  it('should be created', () => {
    expect(component).toBeTruthy();
  });

  it('should add new day to workout', () => {
    setComponent();
    expect(component.workout.days.length).toEqual(0);
    fixture.nativeElement.querySelector('#addDayBtn').click();
    expect(component.workout.days.length).toEqual(1);
  });

  it('should add new exercise set to workout day', () => {
    setComponent();
    const exercise = EXERCISES[0];
    const sets = 4;
    const reps = 10;
    component.exercises[0] = exercise;
    component.sets[0] = sets;
    component.reps[0] = reps;

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
    setComponent();

    const daysAmount = component.workout.days.length;
    fixture.nativeElement.querySelector('#addDayBtn').click();
    expect(component.workout.days.length).toEqual(daysAmount + 1);
    fixture.detectChanges();

    fixture.nativeElement.querySelector('button.removeDayBtn').click();
    expect(component.workout.days.length).toEqual(daysAmount);
    let index = 0;
    for (const day of component.workout.days) {
      expect(day.index).toEqual(index);
      index++;
    }
  });

});
