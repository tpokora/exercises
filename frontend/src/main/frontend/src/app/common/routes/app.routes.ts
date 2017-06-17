import { NgModule } from '@angular/core';
import { ExerciseListComponent } from './../../exercises/exercise-list/exercise-list.component';
import { ExerciseComponent } from './../../exercises/exercise/exercise.component';
import { HomeComponent } from './../../home/home.component';
import { Routes, RouterModule } from '@angular/router';


export const appRoutes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'exercises', component: ExerciseListComponent },
    { path: 'exercise/:exercise_id', component: ExerciseComponent }
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
