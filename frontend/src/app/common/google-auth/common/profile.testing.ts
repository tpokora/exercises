import { Profile } from './profile.model';
import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import 'rxjs/add/observable/of';

export const profile = { name: 'profile name', email: 'profile@email.com' }

@Injectable()
export class ProfileServiceTests {

    isSignedIn(): Observable<boolean> {
        return Observable.of(true);
    }

    getProfile(): Observable<Profile> {
        return Observable.of(profile);
    }

}