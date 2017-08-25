import { BaseService } from './../../common/baseService';
import { Workout } from './workout.model';
import { Http } from '@angular/http/';
import { Utils } from './../../common/utils';
import { Injectable } from '@angular/core';

@Injectable()
export class WorkoutService extends BaseService {

    private url = Utils.getRestApiUrl('workouts');
    private headers = Utils.headers_json();

    constructor(private http: Http) {
        super();
    }

    getAllWorkouts(): Promise<Workout[]> {
        return this.http.get(this.url)
            .toPromise()
            .then(response => response.json() as Workout[])
            .catch(this.handleError);
    }
}
