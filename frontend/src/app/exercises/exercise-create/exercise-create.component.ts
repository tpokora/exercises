import { Subscription } from 'rxjs/Subscription';
import { ProfileService } from './../../common/google-auth/common/profile.service';
import { Exercise } from './../common/exercise.model';
import { ExerciseService } from './../common/exercise.service';
import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-exercise-create',
  templateUrl: './exercise-create.component.html',
  styleUrls: ['./exercise-create.component.css', './../../app.component.css']
})
export class ExerciseCreateComponent implements OnInit, AfterViewChecked {

  private exercise: Exercise;

  private isSignedIn;
  private subscription: Subscription;

  constructor(private profileService: ProfileService, private exerciseService: ExerciseService,
    private route: ActivatedRoute, private router: Router) {
    this.initializeComponent();
    this.initializeExercise();
  }

  ngOnInit() {
    this.getExercise();
  }

  ngAfterViewChecked() {
    this.authenticationCheck();
  }

  getExercise() {
    this.route.params.subscribe(param => {
      this.exerciseService.getExercise(param['exercise_id']).then(exercise => this.exercise = exercise);
    });
  }

  initializeComponent() {
    this.isSignedIn = this.profileService.getSignedIn();
    this.subscription = this.profileService.isSignedIn().subscribe(isSignedIn => {
      this.isSignedIn = isSignedIn;
    });
  }

  create() {
    this.exerciseService.createExercise(this.exercise).then(response => this.navigateList());
  }

  formValid(): boolean {
    return this.exercise.name.length > 3;
  }

  authenticationCheck() {
    if (!this.isSignedIn) {
      this.navigateHome();
    }
  }

  navigateHome() {
    this.router.navigate(['/']);
  }

  navigateList() {
    this.router.navigate(['/exercise-list']);
  }

  private initializeExercise() {
    this.exercise = new Exercise();
    this.exercise.name = '';
    this.exercise.description = '';
  }
}
