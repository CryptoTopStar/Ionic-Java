import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/map';

interface BroadcastEvent {
    key: any;
    data?: any;
}

@Injectable()
export class ThemeService {
  private _darkTheme: Subject<BroadcastEvent> = new Subject<BroadcastEvent>();
  isDarkTheme = this._darkTheme.asObservable();

  changeTheme(key: any, data?: any) {
    this._darkTheme.next({key , data});
  }

  onChange<T>(key: any): Observable<T> {
    return this._darkTheme.asObservable()
      .filter(event => event.key === key)
      .map(event => <T>event.data);
  }
}