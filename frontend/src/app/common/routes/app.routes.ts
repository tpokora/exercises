import { WorkoutDetailComponent } from './../../workouts/workout-detail/workout-detail.component';
import { WorkoutCreateComponent } from './../../workouts/workout-create/workout-create.component';
import { WorkoutListComponent } from './../../workouts/workout-list/workout-list.component';
import { ExerciseCreateComponent } from './../../exercises/exercise-create/exercise-create.component';
import { NgModule } from '@angular/core';
import { ExerciseListComponent } from './../../exercises/exercise-list/exercise-list.component';
import { ExerciseComponent } from './../../exercises/exercise/exercise.component';
import { HomeComponent } from './../../home/home.component';
import { Routes, RouterModule } from '@angular/router';


export const appRoutes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'exercise-list', component: ExerciseListComponent },
    { path: 'exercise/:exercise_id', component: ExerciseComponent },
    { path: 'exercise-create', component: ExerciseCreateComponent },
    { path: 'exercise-create/:exercise_id', component: ExerciseCreateComponent },
    { path: 'workout-list', component: WorkoutListComponent },
    { path: 'workout-create', component: WorkoutCreateComponent },
    { path: 'workout/:workout_id', component: WorkoutDetailComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
    ],
    exports: [
        RouterModule
    ],
    declarations: []
})

export class RoutingModule { }
