import { Home } from './home.model';
import { Http } from '@angular/http/';
import { Injectable } from '@angular/core';
import { environment } from './../../../environments/environment';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class HomeService {

    private url = environment.serverUrl + '/home';
    private headers = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }

    getHome(): Promise<Home> {
        return this.http.get(this.url)
            .toPromise()
            .then(response => response.json() as Home)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occured', error);
        return Promise.reject(error.message || error);
    }
}
