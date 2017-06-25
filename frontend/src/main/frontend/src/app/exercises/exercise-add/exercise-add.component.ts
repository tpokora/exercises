import { Exercise } from './../common/exercise.model';
import { ExerciseService } from './../common/exercise.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-exercise-add',
  templateUrl: './exercise-add.component.html',
  styleUrls: ['./exercise-add.component.css']
})
export class ExerciseAddComponent implements OnInit {

  private exercise: Exercise;

  constructor(private exerciseService: ExerciseService) { }

  ngOnInit() {
    this.exercise = new Exercise();
  }

}
