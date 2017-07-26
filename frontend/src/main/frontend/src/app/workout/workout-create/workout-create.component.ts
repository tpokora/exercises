import { Day } from './../common/day.model';
import { Workout } from './../common/workout.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-workout-create',
  templateUrl: './workout-create.component.html',
  styleUrls: ['./workout-create.component.css']
})
export class WorkoutCreateComponent implements OnInit {

  workout: Workout;

  constructor() {
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

  initializeWorkout() {
    this.workout = new Workout();
    this.workout.days = new Array<Day>();
  }

}
