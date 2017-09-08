import { Subscription } from 'rxjs/Subscription';
import { ProfileService } from './../../common/google-auth/common/profile.service';
import { ModalComponent } from './../../common/modal/modal.component';
import { Workout } from './../common/workout.model';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { WorkoutService } from './../../workouts/common/workout.service';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-workout-detail',
  templateUrl: './workout-detail.component.html',
  styleUrls: ['./workout-detail.component.css', './../../app.component.css']
})
export class WorkoutDetailComponent implements OnInit {

  @ViewChild('deleteWorkoutModal')
  deleteWorkoutModal: ModalComponent;

  workout: Workout;

  private isSignedIn: boolean;
  private subscription: Subscription;

  constructor(private workoutService: WorkoutService, private profileService: ProfileService,
    private route: ActivatedRoute, private router: Router) {
    this.workout = new Workout();
  }

  ngOnInit() {
    this.initializeComponent();
    this.getWorkout();
  }

  initializeComponent() {
    this.isSignedIn = this.profileService.getSignedIn();
    this.subscription = this.profileService.isSignedIn().subscribe(isSignedIn => {
      this.isSignedIn = isSignedIn;
    });
  }

  getWorkout() {
    this.route.params.subscribe(param => {
      this.workoutService.getWorkout(param['workout_id']).then(workout => this.workout = workout);
    });
  }

  hasDays(): boolean {
    return this.workout.days !== undefined && this.workout.days.length > 0;
  }

  hasExerciseSet(id: number): boolean {
    return this.workout.days[id].exerciseSets !== undefined && this.workout.days[id].exerciseSets.length > 0;
  }

  navigateList() {
    this.router.navigate(['/workout-list']);
  }

  deleteWorkout() {
    this.deleteWorkoutModal.hide();
    this.workoutService.deleteWorkout(this.workout.id).then(response => this.navigateList());
  }

}
