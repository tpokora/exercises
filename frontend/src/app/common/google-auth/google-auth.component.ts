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

    this.ngzone.run(() => {
      this.profile.name = profile.getName();
      this.profile.email = profile.getEmail();
      this.signedIn = true;
      this.profileService.signIn(this.profile);
    });
  }

  renderBtn() {
    gapi.signin2.render('googleAuthBtn', {
      'onsuccess': param => this.onSignIn(param),
      'scope': 'profile email',
      'longtitle': true,
      'theme': 'light'
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
