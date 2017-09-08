import { ProfileService } from './../../common/google-auth/common/profile.service';
import { Subscription } from 'rxjs/Subscription';
import { ExerciseService } from './../common/exercise.service';
import { Exercise } from './../common/exercise.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css']
})
export class ExerciseComponent implements OnInit {

  exercise: Exercise;

  private isSignedIn;
  private subscription: Subscription;

  constructor(private exerciseService: ExerciseService, private profileService: ProfileService,
    private route: ActivatedRoute, private router: Router) {
    this.exercise = new Exercise();
  }

  ngOnInit() {
    this.initializeComponent();
    this.getExercise();
  }

  initializeComponent() {
    this.isSignedIn = this.profileService.getSignedIn();
    this.subscription = this.profileService.isSignedIn().subscribe(isSignedIn => {
      this.isSignedIn = isSignedIn;
    });
  }

  getExercise() {
    this.route.params.subscribe(param => {
      this.exerciseService.getExercise(param['exercise_id']).then(exercise => this.exercise = exercise);
    });
  }

  deleteExercise() {
    this.exerciseService.deleteExerciseById(this.exercise.id).then(response => this.navigateList());
  }

  navigateList() {
    this.router.navigate(['/exercise-list']);
  }

}
