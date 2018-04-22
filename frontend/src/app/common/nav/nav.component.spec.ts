import { ProfileServiceTests } from './../google-auth/common/profile.testing';
import { ProfileService } from './../google-auth/common/profile.service';
import { GoogleAuthComponent } from './../google-auth/google-auth.component';
import { HttpModule } from '@angular/http';
import { Router } from '@angular/router';
import { RouterStub, ActivateRouteStub, RouterLinkStub } from './../routes/routing.spec';
import { By } from '@angular/platform-browser';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavComponent } from './nav.component';

describe('NavComponent', () => {
  let component: NavComponent;
  let fixture: ComponentFixture<NavComponent>;
  let routerStub: RouterStub;
  let activatedRoute: ActivateRouteStub;

  beforeEach(async(() => {
    activatedRoute = new ActivateRouteStub();
    TestBed.configureTestingModule({
      declarations: [
        NavComponent,
        RouterLinkStub,
        GoogleAuthComponent
      ],
      providers: [
        { provide: ProfileService, useClass: ProfileServiceTests },
        { provide: Router, useClass: RouterStub }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
