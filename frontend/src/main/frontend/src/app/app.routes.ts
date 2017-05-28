import { ExerciseComponent } from './exercise/exercise.component';
import { HomeComponent } from './home/home.component';
import { Routes } from '@angular/router';


export const appRoutes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'exercise', component: ExerciseComponent }
];
