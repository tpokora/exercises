import { Exercise } from './../../exercises/common/exercise.model';
import { ExerciseSet } from './exerciseSet.model';

export class Day {
    name: string;
    index: number;
    exerciseSets: ExerciseSet[] = new Array<ExerciseSet>();
}
