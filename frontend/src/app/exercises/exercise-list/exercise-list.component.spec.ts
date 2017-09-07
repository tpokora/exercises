import { ProfileServiceTests } from './../../common/google-auth/common/profile.testing';
import { ProfileService } from './../../common/google-auth/common/profile.service';
import { ExerciseServiceTests } from './../common/exercise.testing';
import { ExerciseService } from './../common/exercise.service';
import { RouterTestingModule } from '@angular/router/testing';
import { async, ComponentFixture, TestBed, tick, fakeAsync } from '@angular/core/testing';

import { ExerciseListComponent } from './exercise-list.component';

describe('ExerciseListComponent', () => {
  let component: ExerciseListComponent;
  let fixture: ComponentFixture<ExerciseListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        ExerciseListComponent
      ],
      imports: [
        RouterTestingModule
      ],
      providers: [
        { provide: ProfileService, useClass: ProfileServiceTests },
        { provide: ExerciseService, useClass: ExerciseServiceTests }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExerciseListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  function createComponent() {
    fixture = TestBed.createComponent(ExerciseListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    tick();
  };

  it('should be created', () => {
    expect(component).toBeTruthy();
  });

  it('should have exercise list length > 0', fakeAsync(() => {
    createComponent();
    let exerciseCount = component.exercises.length;
    expect(exerciseCount).toBeGreaterThan(0);
  }));
});
