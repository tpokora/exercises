import { Injectable } from '@angular/core';
import { Workout } from './workout.model';
import { Day } from './day.model';

export const WORKOUTS: Workout[] = [
    { id: 1, name: 'testWorkout1', description: 'testWorkoutDescription', days: new Array<Day>() },
    { id: 2, name: 'testWorkout2', description: 'testWorkoutDescription', days: new Array<Day>() }
];

@Injectable()
export class WorkoutServiceTest {

    getAllWorkouts(): Promise<Workout[]> {
        return Promise.resolve(WORKOUTS);
    }
}
