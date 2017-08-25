import { WorkoutService } from './../common/workout.service';
import { WorkoutServiceTest } from './../common/workout.testing';
import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

import { WorkoutListComponent } from './workout-list.component';

describe('WorkoutListComponent', () => {
  let component: WorkoutListComponent;
  let fixture: ComponentFixture<WorkoutListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [WorkoutListComponent],
      imports: [],
      providers: [
        { provide: WorkoutService, useClass: WorkoutServiceTest }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  function createComponentAsync() {
    fixture = TestBed.createComponent(WorkoutListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    tick();
  }

  it('should be created', () => {
    expect(component).toBeTruthy();
  });

  it('should have workouts', fakeAsync(() => {
    createComponentAsync();
    const workoutsCount = component.workoutsList.length;
    expect(workoutsCount).toBeGreaterThan(0);
  }));
});
