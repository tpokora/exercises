
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
  exercise: Exercise;

  sets: number;
  reps: number;

  DAYS_LIMIT = 7;

  constructor(private exerciseService: ExerciseService) {
    this.initializeWorkout();
  }

  ngOnInit() {
  }

  addDay() {
    if (this.workout.days.length < this.DAYS_LIMIT) {
      let newDay = new Day();
      newDay.index = this.workout.days.length;
      this.workout.days.push(newDay);
    }
  }

  searchExercise = (text$: Observable<string>) =>
    text$
      .debounceTime(100)
      .distinctUntilChanged()
      .switchMap(term => term.length > 3 ? this.exerciseService.getExercisesByName(term) : []);

  addExerciseSet(dayIndex: number) {
    let exercieSet = new ExerciseSet();
    exercieSet.exercise = this.exercise;
    exercieSet.sets = this.sets;
    exercieSet.reps = this.reps;
    this.workout.days[dayIndex].exerciseSets.push(exercieSet);
    this.exercise = new Exercise();
    this.sets = 0;
    this.reps = 0;
  }

  exerciseFormatter(exercise: Exercise): string {
    return exercise.name ? exercise.name : '';
  }

  initializeWorkout() {
    this.workout = new Workout();
    this.workout.days = new Array<Day>();
  }

  dayHasExerciseSets(day: Day): boolean {
    return day.exerciseSets !== undefined && day.exerciseSets.length > 0;
  }

  removeDay(dayIndex: number) {
    this.workout.days.splice(dayIndex, 1);
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
