import { profile } from './common/profile.testing';
import { ProfileService } from './common/profile.service';
import { Profile } from './common/profile.model';
import { environment } from './../../../environments/environment.prod';
import { Component, AfterViewInit, NgZone, ViewChild, Output, Input, OnInit, EventEmitter } from '@angular/core';
import { NgbPopover } from '@ng-bootstrap/ng-bootstrap';

declare var gapi: any;

@Component({
  selector: 'app-google-auth',
  templateUrl: './google-auth.component.html',
  styleUrls: ['./google-auth.component.css']
})
export class GoogleAuthComponent implements AfterViewInit {

  private clientId: string = environment.googleClientId;

  private auth2: any;

  private profile: Profile;

  private signedIn = false;

  @ViewChild('signInPopover') signInPopover: NgbPopover;

  constructor(private profileService: ProfileService, private ngzone: NgZone) {
    this.profile = new Profile();
  }

  ngAfterViewInit() {
    this.googleInit();
    this.renderBtn();
  }

  googleInit() {
    const component = this;
    gapi.load('auth2', () => {
      gapi.auth2.init({
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
            this.emitProfile();
          } else {
            this.signOut();
            this.showLoginErrorPopover();
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
      onSuccess: param => this.onSignIn(param),
      scope: 'profile email'
    });
  }

  signOut() {
    this.auth2 = gapi.auth2.getAuthInstance();
    this.auth2.signOut();
    this.profileService.signOut();
    this.profile = new Profile();
    this.emitProfile();
    this.signedIn = false;
  }

  isSignedIn(): boolean {
    return this.signedIn;
  }

  private emitProfile() {
    this.profileService.onProfileLogged.emit(this.profile);
  }

  private saveUserInSession() {
    localStorage.setItem('user', this.profile.id.toString());
    localStorage.setItem('token', this.profile.token);
  }

  private clearSessionUser() {
    localStorage.clear();
    localStorage.removeItem('user');
    localStorage.removeItem('token');
  }

  private showLoginErrorPopover(): void {
    this.signInPopover.open();
    this.hideSignInErrorPopup();
  }

  private hideSignInErrorPopup(): Promise<any> {
    return new Promise<number>(resolve => {
      setTimeout(() => {
        this.signInPopover.close();
      }, 3000);
  });
  }
}
