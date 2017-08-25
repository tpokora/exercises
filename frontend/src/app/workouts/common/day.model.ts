import { Exercise } from './../../exercises/common/exercise.model';
import { ExerciseSet } from './exerciseSet.model';

export class Day {
    name: string;
    exerciseSets: ExerciseSet[] = new Array<ExerciseSet>();

    addExerciseSet(exercise: Exercise, sets: number, reps: number) {
        let newExerciseSet = new ExerciseSet();
        newExerciseSet.exercise = exercise;
        newExerciseSet.sets = sets;
        newExerciseSet.reps = reps;
        this.exerciseSets.push(newExerciseSet);
    }

    hasExerciseSet(): boolean {
        return this.exerciseSets !== undefined && this.exerciseSets.length > 0;
    }
}
