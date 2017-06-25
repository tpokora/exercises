import { Exercise } from './exercise.model';
import { Injectable } from '@angular/core';

export const EXERCISES: Exercise[] = [
    { id: 1, name: 'testExercise1', description: 'testExerciseDesc1' },
    { id: 2, name: 'testExercise2', description: 'testExerciseDesc2' },
    { id: 3, name: 'testExercise3', description: 'testExerciseDesc3' }
]

@Injectable()
export class ExerciseServiceTests {

    getExercises(): Promise<Exercise[]> {
        return Promise.resolve(EXERCISES);
    }

    getExercise(id: number): Promise<Exercise> {
        return Promise.resolve(EXERCISES.find(exercise => exercise.id === id));
    }

}

