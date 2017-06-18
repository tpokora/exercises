//import { HomeService } from './common/home.service';
import { Component, OnInit } from '@angular/core';
import { Home } from './common/home.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  home: Home = new Home();

  //constructor(private homeService: HomeService) { }
  constructor() { };

  ngOnInit() {
    //this.getHome();
  }

  // getHome() {
  //   this.homeService.getHome()
  //     .then(home => this.home = home);
  // }

}
