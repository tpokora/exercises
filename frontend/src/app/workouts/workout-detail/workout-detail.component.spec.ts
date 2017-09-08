import { ProfileServiceTests } from './../../common/google-auth/common/profile.testing';
import { ProfileService } from './../../common/google-auth/common/profile.service';
import { ModalComponent } from './../../common/modal/modal.component';
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
      declarations: [WorkoutDetailComponent, ModalComponent],
      imports: [
        RouterTestingModule
      ],
      providers: [
        { provide: ProfileService, useClass: ProfileServiceTests },
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
    const workoutName = fixture.debugElement.query(By.css('div.jumbotron h1'));
    expect(workoutName.nativeElement.innerText).toEqual(component.workout.name);
    const workoutDesc = fixture.debugElement.query(By.css('div.jumbotron div.list-desc'));
    expect(workoutDesc.nativeElement.innerText).toEqual(component.workout.description);
  }));
});
