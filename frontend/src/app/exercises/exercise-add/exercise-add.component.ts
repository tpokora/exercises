import { Exercise } from './../common/exercise.model';
import { ExerciseService } from './../common/exercise.service';
import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-exercise-add',
  templateUrl: './exercise-add.component.html',
  styleUrls: ['./exercise-add.component.css', './../../app.component.css']
})
export class ExerciseAddComponent implements OnInit {

  private exercise: Exercise;

  constructor(private exerciseService: ExerciseService, private router: Router) {
    this.initializeExercise();
  }

  ngOnInit() {
  }

  create() {
    this.exerciseService.createExercise(this.exercise);
    this.navigateList();
  }

  formValid(): boolean {
    return this.exercise.name.length > 3;
  }

  navigateList() {
    this.router.navigate(['/exercise-list']);
  }

  private initializeExercise() {
    this.exercise = new Exercise();
    this.exercise.name = "";
    this.exercise.description = "";
  }
}
