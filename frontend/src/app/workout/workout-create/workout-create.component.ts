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
  styleUrls: ['./workout-create.component.css']
})
export class WorkoutCreateComponent implements OnInit {

  workout: Workout;
  exercises: Exercise[];
  exercise: Exercise;

  sets: number;
  reps: number;

  constructor(private exerciseService: ExerciseService) {
    this.initializeWorkout();
  }

  ngOnInit() {
  }

  addDay() {
    if (this.workout.days.length < 7) {
      let newDay = new Day();
      this.workout.days.push(newDay);
    }
  }

  searchExercise = (text$: Observable<string>) =>
    text$
      .debounceTime(100)
      .distinctUntilChanged()
      .switchMap(term => term.length > 3 ? this.exerciseService.getExercisesByName(term) : []);

  addExerciseSet(dayIndex: number) {
    this.workout.days[dayIndex].addExerciseSet(this.exercise, this.sets, this.reps);
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

}
