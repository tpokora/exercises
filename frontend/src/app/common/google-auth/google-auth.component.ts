import { profile } from './common/profile.testing';
import { ProfileService } from './common/profile.service';
import { Profile } from './common/profile.model';
import { environment } from './../../../environments/environment.prod';
import { Component, AfterViewInit, NgZone } from '@angular/core';
declare var gapi: any;

@Component({
  selector: 'app-google-auth',
  templateUrl: './google-auth.component.html',
  styleUrls: ['./google-auth.component.css']
})
export class GoogleAuthComponent implements AfterViewInit {

  private clientId: string = environment.googleClientId;

  private auth2: any;

  private profile = new Profile();
  private signedIn = false;

  constructor(private profileService: ProfileService, private ngzone: NgZone) { }

  ngAfterViewInit() {
    this.googleInit();
    this.renderBtn();
  }

  googleInit() {
    const component = this;
    gapi.load('auth2', () => {
      component.auth2 = gapi.auth2.init({
        client_id: component.clientId,
      });
    });
  }

  onSignIn(googleUser) {
    const profile = googleUser.getBasicProfile();
    const id_token = googleUser.getAuthResponse().id_token;

    this.ngzone.run(() => {
      this.profileService.authenticateToken(id_token).then(profile => {
          if (this.isUserValid(profile)) {
            this.profile = profile;
            this.signedIn = true;
            this.profileService.signIn(this.profile);
          } else {
            this.signOut();
          }
      });
    });
  }

  private isUserValid(profile: any): boolean {
    if (profile === 0) {
      return false;
    }
    return true;
  }

  renderBtn() {
    gapi.signin2.render('googleAuthBtn', {
      'onsuccess': param => this.onSignIn(param),
      'scope': 'profile email',
      'longtitle': true,
      'theme': 'dark'
    });
  }

  signOut() {
    this.auth2 = gapi.auth2.getAuthInstance();
    this.auth2.signOut();
    this.profileService.signOut();
    this.profile = new Profile();
    this.signedIn = false;
  }

  isSignedIn(): boolean {
    return this.signedIn;
  }
}
