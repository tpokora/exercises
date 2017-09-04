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

  it('should be created', () => {
    expect(component).toBeTruthy();
  });

  it('should have navbar title "Navigation"', () => {
    const navTitle = 'Navigation';
    expect(component.title).toEqual(navTitle);
    const navbarTitle = fixture.debugElement.query(By.css('nav div.container div span.navbar-brand'));
    expect(navbarTitle.nativeElement.innerText).toEqual(navTitle);
  });

  it('should have list of navigation', () => {
    const navbarList = fixture.debugElement.queryAll(By.css('nav ul li a'));
    expect(navbarList[0].nativeElement.text).toEqual('>> Home');
    expect(navbarList[1].nativeElement.text).toEqual('>> Exercises');
  });
});
