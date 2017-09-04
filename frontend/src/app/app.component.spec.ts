import { ProfileService } from './common/google-auth/common/profile.service';
import { GoogleAuthComponent } from './common/google-auth/google-auth.component';
import { ExerciseServiceTests } from './exercises/common/exercise.testing';
import { ExerciseService } from './exercises/common/exercise.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ExerciseListComponent } from './exercises/exercise-list/exercise-list.component';
import { ExerciseComponent } from './exercises/exercise/exercise.component';
import { NavComponent } from './common/nav/nav.component';
import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { RouterOutlet } from '@angular/router';

import { AppComponent } from './app.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        NavComponent,
        ExerciseComponent,
        ExerciseListComponent,
        GoogleAuthComponent
      ],
      imports: [
        RouterTestingModule,
        NgbModule.forRoot()
      ],
      providers: [
        { provide: ProfileService },
        { provide: ExerciseService, useClass: ExerciseServiceTests }
      ]
    }).compileComponents();
  }));

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));

  it('should contain NavComponent', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const navbar = fixture.debugElement.query(By.directive(NavComponent));
    expect(navbar).toBeTruthy();
  }));

  it('should have router-outlet component', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const routerOutlet = fixture.debugElement.query(By.directive(RouterOutlet));
    expect(routerOutlet).toBeTruthy();
  });
});
