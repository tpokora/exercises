import { ExerciseServiceTests } from './../../exercises/common/exercise.testing';
import { ExerciseService } from './../../exercises/common/exercise.service';
import { WORKOUT } from './../common/workout.testing';
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
    component.workout = WORKOUT;
    expect(component.workout.days.length).toEqual(0);
    fixture.nativeElement.querySelector('#addWorkoutBtn').click();
    expect(component.workout.days.length).toEqual(1);
  });

  it('should add new exercise set to workout day', () => {
    component.workout = WORKOUT;
    fixture.nativeElement.querySelector('#addWorkoutBtn').click();
    expect(component.workout.days[0].exerciseSets.length).toEqual(0);
    fixture.nativeElement.querySelector('#addExerciseSetBtn').click();
    //expect(component.workout.days[0].exerciseSets.length).toEqual(1);
  });

});
