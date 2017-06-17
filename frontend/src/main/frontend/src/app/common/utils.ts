import { environment } from './../../environments/environment';
export class Utils {

    static getServerUrl(url: string): string {
        return environment.serverUrl + '/' + url;
    }
    static headers_json() {
        return new Headers({ 'Content-Type': 'application/json' });
    }
}


