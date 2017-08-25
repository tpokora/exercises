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

  constructor(
    private exerciseService: ExerciseService
  ) { }

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
