import { Observable } from 'rxjs/Observable';
import { Profile } from './profile.model';
import { BaseService } from './../../baseService';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ProfileService extends BaseService {

    private profile: Profile;
    private signedIn = false;

    private subject = new Subject<Profile>();

    signIn(profile: Profile) {
        this.profile = profile;
        this.signedIn = true;
        this.subject.next(this.profile);
    }

    signOut() {
        this.profile = new Profile();
        this.signedIn = false;
        this.subject.next(new Profile());
    }

    isSignedIn(): boolean {
        return this.signedIn;
    }

    getProfile(): Observable<Profile> {
        return this.subject.asObservable();
    }

}
