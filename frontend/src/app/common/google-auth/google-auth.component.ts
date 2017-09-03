import { element } from 'protractor';
import { environment } from './../../../environments/environment.prod';
import { Component, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
declare var gapi: any;

@Component({
  selector: 'app-google-auth',
  templateUrl: './google-auth.component.html',
  styleUrls: ['./google-auth.component.css']
})
export class GoogleAuthComponent implements AfterViewInit {

  private clientId: string = environment.googleClientId;

  private auth2: any;

  @ViewChild('googleBtn') googleBtn: ElementRef;

  constructor(private element: ElementRef) { }

  ngAfterViewInit(): void {
    this.googleInit();
  }

  googleInit() {
    const component = this;
    gapi.load('auth2', () => {
      component.auth2 = gapi.auth2.init({
        client_id: component.clientId,
      });
      component.signIn(this.element.nativeElement.firstChild);
    });
  }

  signIn(element) {
    this.auth2.attachClickHandler(element, {},
      function (googleUser) {
        const profile = googleUser.getBasicProfile();
        console.log('Email: ' + profile.getEmail());
      }
      , function (error) {
        console.log(JSON.stringify(error, undefined, 2));
      });
  }

}
