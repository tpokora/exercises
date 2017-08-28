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

    getWorkout(id: number): Promise<Workout> {
        const url = `${this.url}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Workout)
            .catch(this.handleError);
    }

    createWorkout(workout: Workout): Promise<Workout> {
        return this.http.post(this.url, JSON.stringify(workout), { headers: this.headers })
            .toPromise()
            .then(response => response.json() as Workout)
            .catch(this.handleError);
    }

    deleteWorkout(id: number) {
        const url = `${this.url}/${id}`;
        return this.http.delete(url)
            .toPromise()
            .then(response => response.ok)
            .catch(this.handleError);
    }
}
