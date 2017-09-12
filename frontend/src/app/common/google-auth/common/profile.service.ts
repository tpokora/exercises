import { Http } from '@angular/http/';
import { Observable } from 'rxjs/Observable';
import { Profile } from './profile.model';
import { BaseService } from './../../baseService';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { Utils } from 'app/common/utils';

@Injectable()
export class ProfileService extends BaseService {

    private url = Utils.getRestApiUrl('auth');
    private headers = Utils.headers_form_urlencoded();

    private profile: Profile;
    private signedIn = false;

    private subjectProfile = new Subject<Profile>();
    private subjectSignedIn = new Subject<boolean>();

    constructor(private http: Http) {
        super();
    }

    signIn(profile: Profile) {
        this.profile = profile;
        this.signedIn = true;
        this.setSubjects(this.profile, this.signedIn);
    }

    signOut() {
        this.profile = new Profile();
        this.signedIn = false;
        this.setSubjects(this.profile, this.signedIn);
    }

    isSignedIn(): Observable<boolean> {
        return this.subjectSignedIn.asObservable();
    }

    getProfile(): Observable<Profile> {
        return this.subjectProfile.asObservable();
    }

    getSignedIn(): boolean {
        return this.signedIn;
    }

    private setSubjects(profile: Profile, signedIn: boolean) {
        this.subjectProfile.next(profile);
        this.subjectSignedIn.next(signedIn);
    }

    authenticateToken(token: string): Promise<Profile> {
        const url = `${this.url}/authtoken`;
        const body = 'token=' + token;
        return this.http.post(url, body, { headers: this.headers })
            .toPromise()
            .then(response => response.json() as Profile)
            .catch(error => error.status);
    }
}
