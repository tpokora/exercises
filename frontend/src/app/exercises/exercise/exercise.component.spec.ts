import { ProfileServiceTests } from './../../common/google-auth/common/profile.testing';
import { ProfileService } from './../../common/google-auth/common/profile.service';
import { ActivatedRoute } from '@angular/router';
import { ActivateRouteStub } from './../../common/routes/routing.spec';
import { ExerciseServiceTests, EXERCISES } from './../common/exercise.testing';
import { ExerciseService } from './../common/exercise.service';
import { RouterTestingModule } from '@angular/router/testing';
import { async, ComponentFixture, TestBed, fakeAsync, tick, getTestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';

import { ExerciseComponent } from './exercise.component';

describe('ExerciseComponent', () => {
  let component: ExerciseComponent;
  let fixture: ComponentFixture<ExerciseComponent>;
  let activatedRoute: ActivateRouteStub;

  beforeEach(async(() => {
    activatedRoute = new ActivateRouteStub();
    TestBed.configureTestingModule({
      declarations: [
        ExerciseComponent
      ],
      imports: [
        RouterTestingModule
      ],
      providers: [
        { provide: ProfileService, useClass: ProfileServiceTests },
        { provide: ExerciseService, useClass: ExerciseServiceTests },
        { provide: ActivatedRoute, useValue: activatedRoute }
      ]
    })
      .compileComponents();
  }));

  function createComponent(exerciseId: number) {
    fixture = TestBed.createComponent(ExerciseComponent);
    component = fixture.componentInstance;
    activatedRoute.testParams = { exercise_id: exerciseId };
    fixture.detectChanges();
    tick();
  }

  it('should be created with exercise', fakeAsync(() => {
    const exercise = EXERCISES[0];
    const id = exercise.id;
    createComponent(id);
    fixture.detectChanges();
    expect(component).toBeTruthy();
    expect(component.exercise).toBeTruthy();
    expect(component.exercise.id).toEqual(id);
    expect(component.exercise.name).toEqual(exercise.name);
    expect(component.exercise.description).toEqual(exercise.description);
    const exerciseName = fixture.debugElement.query(By.css('div.jumbotron h2'));
    expect(exerciseName.nativeElement.innerText).toEqual(component.exercise.name);
    const exerciseDesc = fixture.debugElement.query(By.css('div.jumbotron div.exercise-desc'));
    expect(exerciseDesc.nativeElement.innerText).toEqual(component.exercise.description);
  }));

});
