import { WorkoutService } from './workouts/common/workout.service';
import { ExerciseService } from './exercises/common/exercise.service';
import { RoutingModule } from './common/routes/app.routes';
import { HomeService } from './home/common/home.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { RouterModule, Routes } from '@angular/router';
import { ExerciseComponent } from './exercises/exercise/exercise.component';
import { NavComponent } from './common/nav/nav.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ExerciseListComponent } from './exercises/exercise-list/exercise-list.component';
import { ExerciseAddComponent } from './exercises/exercise-add/exercise-add.component';
import { WorkoutListComponent } from './workouts/workout-list/workout-list.component';
import { WorkoutCreateComponent } from './workouts/workout-create/workout-create.component';
import { WorkoutDetailComponent } from './workouts/workout-detail/workout-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ExerciseComponent,
    NavComponent,
    ExerciseListComponent,
    ExerciseAddComponent,
    WorkoutListComponent,
    WorkoutCreateComponent,
    WorkoutDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RoutingModule,
    NgbModule.forRoot()
  ],
  providers: [
    HomeService,
    ExerciseService,
    WorkoutService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
