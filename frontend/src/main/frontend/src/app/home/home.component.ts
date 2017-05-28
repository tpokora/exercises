import { HomeService } from './shared/home.service';
import { Component, OnInit } from '@angular/core';
import { Home } from './shared/home.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  home: Home;

  constructor(private homeService: HomeService) { }

  ngOnInit() {
    //this.getHome();
  }

  getHome() {
    this.homeService.getHome()
      .then(home => this.home = home);
  }

}
