import { Injectable } from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent,
  HttpResponse, HttpEvent, HttpErrorResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { TokenStorageService } from './auth/token.storage.service';
import 'rxjs/add/operator/do';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenStorageService, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
  Observable<HttpEvent<any>> {

  let authReq = req;
  if (this.token.getToken() != null) {
    authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())});
  }

  return next.handle(authReq).do(
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
         if (err.status === 401) {
              this.router.navigate(['auth/login', { queryParams:  'error' , skipLocationChange: true}]);
          }
        }
      }
    );
}

}