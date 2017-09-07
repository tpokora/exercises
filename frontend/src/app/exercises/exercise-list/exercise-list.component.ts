import { Subscription } from 'rxjs/Subscription';
import { ProfileService } from './../../common/google-auth/common/profile.service';
import { ExerciseService } from './../common/exercise.service';
import { Exercise } from './../common/exercise.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-exercise-list',
  templateUrl: './exercise-list.component.html',
  styleUrls: ['./exercise-list.component.css', './../../app.component.css']
})
export class ExerciseListComponent implements OnInit {

  exercises: Exercise[];

  private isSignedIn: boolean;
  private subscription: Subscription;

  constructor(
    private exerciseService: ExerciseService, private profileService: ProfileService) {
    this.isSignedIn = this.profileService.getSignedIn();
    this.subscription = this.profileService.isSignedIn().subscribe(isSignedIn => {
      this.isSignedIn = isSignedIn;
    });
  }

  ngOnInit() {
    this.getExercises();
  }

  getExercises() {
    this.exerciseService.getExercises()
      .then(exercises => this.exercises = exercises);
  }

  hasExercises(): boolean {
    return this.exercises !== undefined && this.exercises.length > 0;
  }

}
