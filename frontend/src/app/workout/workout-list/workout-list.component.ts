import { Workout } from './../common/workout.model';
import { WorkoutService } from './../common/workout.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-workout-list',
  templateUrl: './workout-list.component.html',
  styleUrls: ['./workout-list.component.css', './../../app.component.css']
})
export class WorkoutListComponent implements OnInit {

  workoutsList: Workout[];

  constructor(private workoutService: WorkoutService) { }

  ngOnInit() {
    this.getWorkouts();
  }

  getWorkouts() {
    this.workoutService.getAllWorkouts().then(workouts => this.workoutsList = workouts);
  }

  hasWorkouts(): boolean {
    return this.workoutsList !== undefined && this.workoutsList.length > 0;
  }

}
