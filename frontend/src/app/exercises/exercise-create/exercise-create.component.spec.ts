import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { ExerciseServiceTests } from './../common/exercise.testing';
import { ExerciseService } from './../common/exercise.service';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExerciseCreateComponent } from './exercise-create.component';

describe('ExerciseAddComponent', () => {
  let component: ExerciseCreateComponent;
  let fixture: ComponentFixture<ExerciseCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ExerciseCreateComponent],
      imports: [
        RouterTestingModule,
        FormsModule
      ],
      providers: [
        {
          provide: ExerciseService, useClass: ExerciseServiceTests
        }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExerciseCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
