import { environment } from './../../environments/environment';
export class Utils {

    static getRestApiUrl(url: string): string {
        return environment.serverUrl + '/rest/' + url;
    }
    static headers_json() {
        return new Headers({ 'Content-Type': 'application/json' });
    }
}


