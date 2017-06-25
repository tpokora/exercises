import { ExerciseService } from './../common/exercise.service';
import { Exercise } from './../common/exercise.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-exercise',
  templateUrl: './exercise.component.html',
  styleUrls: ['./exercise.component.css']
})
export class ExerciseComponent implements OnInit {

  private exerciseId;
  private exercise: Exercise;

  constructor(private exerciseService: ExerciseService, private route: ActivatedRoute) {
    this.exercise = new Exercise();
  }

  ngOnInit() {
    this.getExerciseId();
    this.getExercise();
  }

  getExerciseId() {
    this.route.params.subscribe(param => this.exerciseId = param['exercise_id']);
  }

  getExercise() {
  }

}
