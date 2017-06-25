import { Injectable } from '@angular/core';
import { Exercise } from './exercise.model';
import { Http } from '@angular/http/';
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

    private handleError(error: any): Promise<any> {
        console.error('An error occured', error);
        return Promise.reject(error.message || error);
    }
}
