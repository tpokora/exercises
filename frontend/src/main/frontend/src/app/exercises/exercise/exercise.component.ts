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

  exercise: Exercise;

  constructor(private exerciseService: ExerciseService, private route: ActivatedRoute) {
    this.exercise = new Exercise();
  }

  ngOnInit() {
    this.getExercise();
  }

  getExercise() {
    this.route.params.subscribe(param => {
      this.exerciseService.getExercise(param['exercise_id']).then(exercise => this.exercise = exercise);
    });
  }

}
