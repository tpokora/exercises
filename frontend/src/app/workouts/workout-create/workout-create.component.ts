import { Observable } from 'rxjs/Observable';
import { ExerciseSet } from './../common/exerciseSet.model';
import { ExerciseService } from './../../exercises/common/exercise.service';
import { Exercise } from './../../exercises/common/exercise.model';
import { Day } from './../common/day.model';
import { Workout } from './../common/workout.model';
import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-workout-create',
  templateUrl: './workout-create.component.html',
  styleUrls: ['./workout-create.component.css', './../../app.component.css']
})
export class WorkoutCreateComponent implements OnInit {

  workout: Workout;
  exercises: Exercise[];

  sets: number[];
  reps: number[];

  DAYS_LIMIT = 7;

  constructor(private exerciseService: ExerciseService) {
    this.initializeWorkout();
    this.initializeExercises();
    this.initializeSets();
    this.initializeReps();
  }

  ngOnInit() {
  }

  initializeWorkout() {
    this.workout = new Workout();
    this.workout.days = new Array<Day>();
  }

  initializeExercises() {
    this.exercises = new Array<Exercise>();
    this.exercises.push(new Exercise());
  }

  initializeSets() {
    this.sets = new Array<number>();
    this.sets.push(0);
  }

  initializeReps() {
    this.reps = new Array<number>();
    this.reps.push(0);
  }

  addDay() {
    if (this.workout.days.length < this.DAYS_LIMIT) {
      const newDay = new Day();
      newDay.index = this.workout.days.length;
      this.workout.days.push(newDay);
      if (this.workout.days.length > 1) {
        this.exercises.push(new Exercise());
        this.sets.push(0);
        this.reps.push(0);
      }
    }
  }

  searchExercise = (text$: Observable<string>) =>
    text$
      .debounceTime(100)
      .distinctUntilChanged()
      .switchMap(term => term.length > 3 ? this.exerciseService.getExercisesByName(term) : []);

  addExerciseSet(dayIndex: number) {
    const exercieSet = new ExerciseSet();
    exercieSet.exercise = this.exercises[dayIndex];
    exercieSet.sets = this.sets[dayIndex];
    exercieSet.reps = this.reps[dayIndex];
    this.workout.days[dayIndex].exerciseSets.push(exercieSet);
    this.exercises[dayIndex] = new Exercise();
    this.sets[dayIndex] = 0;
    this.reps[dayIndex] = 0;
  }

  addExerciseSetValidation(dayIndex: number): boolean {
    return this.exercises[0].name !== undefined && this.sets[dayIndex] > 0 && this.reps[dayIndex] > 0;
  }

  exerciseFormatter(exercise: Exercise): string {
    return exercise.name ? exercise.name : '';
  }

  dayHasExerciseSets(day: Day): boolean {
    return day.exerciseSets !== undefined && day.exerciseSets.length > 0;
  }

  removeDay(dayIndex: number) {
    this.workout.days.splice(dayIndex, 1);
    this.exercises.splice(dayIndex, 1);
    this.sets.splice(dayIndex, 1);
    this.reps.splice(dayIndex, 1);
    for (let i = 0; i < this.workout.days.length; i++) {
      if (this.workout.days[i].index !== i) {
        this.workout.days[i].index = i;
      }
    }
  }

  hasDaysLimit(): boolean {
    return this.workout.days.length >= this.DAYS_LIMIT;
  }

}
