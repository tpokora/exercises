import { Profile } from './profile.model';
import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import 'rxjs/add/observable/of';

export const profile: Profile = { id: 1, name: 'profile name', email: 'profile@email.com', token: 'token' }

@Injectable()
export class ProfileServiceTests {

    isSignedIn(): Observable<boolean> {
        return Observable.of(true);
    }

    getProfile(): Observable<Profile> {
        return Observable.of(profile);
    }

    getSignedIn(): boolean {
        return true;
    }

}