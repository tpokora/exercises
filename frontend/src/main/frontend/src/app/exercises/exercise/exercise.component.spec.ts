import { ActivatedRoute } from '@angular/router';
import { ActivateRouteStub } from './../../common/routes/routing.spec';
import { ExerciseServiceTests } from './../common/exercise.testing';
import { ExerciseService } from './../common/exercise.service';
import { RouterTestingModule } from '@angular/router/testing';
import { async, ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';

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
        { provide: ExerciseService, useClass: ExerciseServiceTests },
        { provide: ActivatedRoute, useValue: activatedRoute }
      ]
    })
      .compileComponents();
  }));

  function createComponent(exerciseId: number) {
    fixture = TestBed.createComponent(ExerciseComponent);
    component = fixture.componentInstance;
    activatedRoute.testParams = { exercise_id: 1 }
    fixture.detectChanges();
    tick();
  }

  it('should be created with exercise', fakeAsync(() => {
    let id = 1;
    createComponent(id);
    expect(component).toBeTruthy();
    expect(component.exercise).toBeTruthy();
    expect(component.exercise.id).toEqual(id);
    expect(component.exercise.name).toEqual("testExercise" + id);
    expect(component.exercise.description).toEqual("testExerciseDesc" + id);
  }));

});
