import { Profile } from './../google-auth/common/profile.model';
import { ProfileService } from './../google-auth/common/profile.service';
import { Component, OnInit } from '@angular/core';
import { Subscription } from "rxjs/Subscription";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  title = 'Navigation';

  private isSignedIn = false;
  private subscription: Subscription;

  constructor(private profileService: ProfileService) {
    this.subscription = this.profileService.isSignedIn().subscribe(isSignedIn => this.isSignedIn = isSignedIn);
  }

  ngOnInit() {
  }

}
