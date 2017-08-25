import { By } from '@angular/platform-browser';
import { WORKOUTS } from './../../workouts/common/workout.testing';
import { Workout } from './../../workouts/common/workout.model';
import { ActivatedRoute } from '@angular/router';
import { ActivateRouteStub } from './../../common/routes/routing.spec';
import { WorkoutServiceTest } from './../../workouts/common/workout.testing';
import { WorkoutService } from './../../workouts/common/workout.service';
import { RouterTestingModule } from '@angular/router/testing';
import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { WorkoutDetailComponent } from './workout-detail.component';

describe('WorkoutDetailComponent', () => {
  let component: WorkoutDetailComponent;
  let fixture: ComponentFixture<WorkoutDetailComponent>;
  let activatedRoute: ActivateRouteStub;

  beforeEach(async(() => {
    activatedRoute = new ActivateRouteStub();
    TestBed.configureTestingModule({
      declarations: [WorkoutDetailComponent],
      imports: [
        RouterTestingModule
      ],
      providers: [
        { provide: WorkoutService, useClass: WorkoutServiceTest },
        { provide: ActivatedRoute, useValue: activatedRoute }
      ]
    })
      .compileComponents();
  }));

  function createComponent(workoutId: number) {
    fixture = TestBed.createComponent(WorkoutDetailComponent);
    component = fixture.componentInstance;
    activatedRoute.testParams = { workout_id: workoutId };
    fixture.detectChanges();
    tick();
  }

  it('should be created with workout', fakeAsync(() => {
    const workout = WORKOUTS[0];
    const id = workout.id;
    createComponent(id);
    fixture.detectChanges();
    expect(component).toBeTruthy();
    expect(component.workout).toBeTruthy();
    expect(component.workout.id).toEqual(id);
    expect(component.workout.name).toEqual(workout.name);
    expect(component.workout.description).toEqual(workout.description);
    let workoutName = fixture.debugElement.query(By.css('div.container h1'));
    expect(workoutName.nativeElement.innerText).toEqual(component.workout.name);
    let workoutDesc = fixture.debugElement.query(By.css('div.container div.list-desc'));
    expect(workoutDesc.nativeElement.innerText).toEqual(component.workout.description);
  }));
});
