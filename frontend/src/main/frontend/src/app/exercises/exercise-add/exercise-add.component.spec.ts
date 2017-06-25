import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { ExerciseServiceTests } from './../common/exercise.testing';
import { ExerciseService } from './../common/exercise.service';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExerciseAddComponent } from './exercise-add.component';

describe('ExerciseAddComponent', () => {
  let component: ExerciseAddComponent;
  let fixture: ComponentFixture<ExerciseAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ExerciseAddComponent],
      imports: [
        RouterTestingModule,
        FormsModule],
      providers: [
        {
          provide: ExerciseService, useClass: ExerciseServiceTests
        }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExerciseAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
