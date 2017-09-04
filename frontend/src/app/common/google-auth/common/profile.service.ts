import { Observable } from 'rxjs/Observable';
import { Profile } from './profile.model';
import { BaseService } from './../../baseService';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ProfileService extends BaseService {

    private profile: Profile;
    private signedIn = false;

    private subjectProfile = new Subject<Profile>();
    private subjectSignedIn = new Subject<boolean>();

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

    private setSubjects(profile: Profile, signedIn: boolean) {
        this.subjectProfile.next(profile);
        this.subjectSignedIn.next(signedIn);
    }

}
