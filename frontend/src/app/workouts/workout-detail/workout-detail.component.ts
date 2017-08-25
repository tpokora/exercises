import { Workout } from './../common/workout.model';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { WorkoutService } from './../../workouts/common/workout.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-workout-detail',
  templateUrl: './workout-detail.component.html',
  styleUrls: ['./workout-detail.component.css']
})
export class WorkoutDetailComponent implements OnInit {

  workout: Workout;

  constructor(private workoutService: WorkoutService, private route: ActivatedRoute, private router: Router) {
    this.workout = new Workout();
  }

  ngOnInit() {
    this.getWorkout();
  }

  getWorkout() {
    this.route.params.subscribe(param => {
      this.workoutService.getWorkout(param['workout_id']).then(workout => this.workout = workout);
    });
  }



}
