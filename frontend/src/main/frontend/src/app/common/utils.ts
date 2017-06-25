import { Headers } from '@angular/http/';
import { environment } from './../../environments/environment';
export class Utils {

    static getRestApiUrl(url: string): string {
        return environment.serverUrl + '/rest/' + url;
    }
    static headers_json(): Headers {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return headers;
    }
}


