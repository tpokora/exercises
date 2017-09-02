import { BaseService } from './../../common/baseService';
import { Utils } from './../../common/utils';
import { Home } from './home.model';
import { Http } from '@angular/http/';
import { Injectable } from '@angular/core';
import { environment } from './../../../environments/environment';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class HomeService extends BaseService {

    private url = Utils.getRestApiUrl('home');
    private headers = Utils.headers_json();

    constructor(private http: Http) {
        super();
    }

    getHome(): Promise<Home> {
        return this.http.get(this.url)
            .toPromise()
            .then(response => response.json() as Home)
            .catch(this.handleError);
    }

}
