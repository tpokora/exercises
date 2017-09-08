import { ProfileService } from './../../common/google-auth/common/profile.service';
import { Subscription } from 'rxjs/Subscription';
import { WorkoutService } from './../common/workout.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { ExerciseSet } from './../common/exerciseSet.model';
import { ExerciseService } from './../../exercises/common/exercise.service';
import { Exercise } from './../../exercises/common/exercise.model';
import { Day } from './../common/day.model';
import { Workout } from './../common/workout.model';
import { Component, OnInit, AfterViewChecked } from '@angular/core';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';

@Component({
  selector: 'app-workout-create',
  templateUrl: './workout-create.component.html',
  styleUrls: ['./workout-create.component.css', './../../app.component.css']
})
export class WorkoutCreateComponent implements OnInit, AfterViewChecked {

  workout: Workout;
  exercises: Exercise[];

  sets: number[];
  reps: number[];

  DAYS_LIMIT = 7;

  private isSignedIn;
  private subscription: Subscription;

  constructor(private profileService: ProfileService, private workoutService: WorkoutService,
    private exerciseService: ExerciseService, private route: ActivatedRoute,
    private router: Router) {
    this.initializeComponent();
  }

  ngOnInit() {
    this.getWorkout();
  }

  ngAfterViewChecked() {
    this.authenticationCheck();
  }

  getWorkout() {
    this.route.params.subscribe(param => {
      if (param['workout_id'] !== undefined) {
        this.workoutService.getWorkout(param['workout_id']).then(workout => this.workout = workout);
      }
    });
  }

  initializeComponent() {
    this.isSignedIn = this.profileService.getSignedIn();
    this.subscription = this.profileService.isSignedIn().subscribe(isSignedIn => {
      this.isSignedIn = isSignedIn;
    });
    this.initializeWorkout();
    this.initializeExercises();
    this.initializeSets();
    this.initializeReps();
  }

  private initializeWorkout() {
    this.workout = new Workout();
    this.workout.days = new Array<Day>();
  }

  private initializeExercises() {
    this.exercises = new Array<Exercise>();
    for (let i = 0; i < this.DAYS_LIMIT; i++) {
      this.exercises.push(new Exercise());
    }
  }

  private initializeSets() {
    this.sets = new Array<number>();
    for (let i = 0; i < this.DAYS_LIMIT; i++) {
      this.sets.push(0);
    }

  }

  private initializeReps() {
    this.reps = new Array<number>();
    for (let i = 0; i < this.DAYS_LIMIT; i++) {
      this.reps.push(0);
    }
  }

  addDay() {
    if (this.workout.days.length < this.DAYS_LIMIT) {
      const newDay = new Day();
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
    return this.exercises[dayIndex].name !== undefined && this.sets[dayIndex] > 0 && this.reps[dayIndex] > 0;
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

  createWorkout() {
    this.workoutService.createWorkout(this.workout).then(response => this.navigateList());
  }

  createWorkoutValidation(): boolean {
    return this.workout.name !== undefined && this.workout.name !== ''
      && this.workout.days.length > 0 && this.workout.days[0].exerciseSets.length > 0;
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
    this.router.navigate(['/workout-list']);
  }

}
