import { ProfileServiceTests } from './common/profile.testing';
import { ProfileService } from './common/profile.service';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoogleAuthComponent } from './google-auth.component';
import { Component } from '@angular/core';

describe('GoogleAuthComponent', () => {
  let component: GoogleAuthComponent;
  let fixture: ComponentFixture<GoogleAuthComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        GoogleAuthComponent
      ],
      providers: [
        { provide: ProfileService, useClass: ProfileServiceTests },
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoogleAuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
