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

  private name: string;
  private email: string;

  private showProfile = false;

  constructor(private ngzone: NgZone) { }

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
    var profile = googleUser.getBasicProfile();

    console.log('Full Name: ' + profile.getName());
    console.log('Given Name: ' + profile.getGivenName());
    console.log('Family Name: ' + profile.getFamilyName());
    console.log("Image URL: " + profile.getImageUrl());
    console.log("Email: " + profile.getEmail());

    this.ngzone.run(() => {
      this.name = profile.getName();
      this.email = profile.getEmail();
      this.showProfile = true;
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
    this.showProfile = false;
  }
}
