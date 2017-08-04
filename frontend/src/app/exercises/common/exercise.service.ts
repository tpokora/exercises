import { Injectable } from '@angular/core';
import { Exercise } from './exercise.model';
import { Http, Headers } from '@angular/http/';
import { Utils } from './../../common/utils';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class ExerciseService {

    private url = Utils.getRestApiUrl('exercises');
    private headers = Utils.headers_json();

    constructor(private http: Http) { }

    getExercises(): Promise<Exercise[]> {
        return this.http.get(this.url)
            .toPromise()
            .then(response => response.json() as Exercise[])
            .catch(this.handleError);
    }

    createExercise(exercise: Exercise): Promise<Exercise> {
        return this.http.post(this.url, JSON.stringify(exercise), { headers: this.headers })
            .toPromise()
            .then(response => response.json() as Exercise)
            .catch(this.handleError);
    }

    getExercise(exerciseId: number): Promise<Exercise> {
        let url = `${this.url}/${exerciseId}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Exercise)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occured', error);
        return Promise.reject(error.message || error);
    }
}
