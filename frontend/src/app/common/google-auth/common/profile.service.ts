import { Profile } from './profile.model';
import { BaseService } from './../../baseService';
import { Injectable } from '@angular/core';

@Injectable()
export class ProfileService extends BaseService {

    private profile: Profile;
    private signedIn = false;

    signIn(profile: Profile) {
        this.profile = profile;
        this.signedIn = true;
    }

    signOut() {
        this.profile = undefined;
        this.signedIn = false;
    }

    isSignedIn(): boolean {
        return this.signedIn;
    }


}
