import { Injectable, Directive, Input } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { NavigationEnd } from "@angular/router";
import { Observable } from "rxjs/Observable";

@Injectable()
export class ActivateRouteStub {

    private subject = new BehaviorSubject(this.testParams);
    params = this.subject.asObservable();

    private _testParams: {};

    get testParams() {
        return this._testParams;
    }

    set testParams(params: {}) {
        this._testParams = params;
        this.subject.next(params);
    }

    get snapshot() {
        return { params: this.testParams };
    }
}

@Injectable()
export class RouterStub {
    public ne = new NavigationEnd(0, '/exercise/123', '/exercise/123');
    public events = new Observable(observer => {
        observer.next(this.ne);
        observer.complete();
    });

    navigate(commands: any[]) {
        return commands;
    }
}

@Directive({
    selector: '[routerLink]'
})
export class RouterLinkStub {
    @Input('routerLink') linkParams: any;
}
