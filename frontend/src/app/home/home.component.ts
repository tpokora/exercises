import { Profile } from './../common/google-auth/common/profile.model';
import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { Home } from './common/home.model';
import { ProfileService } from '../common/google-auth/common/profile.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @Input() profile: Profile;

  constructor(private profileService: ProfileService) { 
    this.profile = new Profile();
    this.profileService.onProfileLogged.subscribe(profile => this.profile = profile);
  };

  ngOnInit() {
  }

  welcomeMessage() {
    const welcome = 'Welcome';
    let outputString = welcome;
    if (this.profile.name.length > 0) {
      outputString += ' ' + this.profile.name;
    }

    outputString += '!';

    return outputString;
  }

}
