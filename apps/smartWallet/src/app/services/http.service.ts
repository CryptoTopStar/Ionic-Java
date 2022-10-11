import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { map , catchError} from 'rxjs/operators';
const SOMETHING_WENT_WRONG = 'Something Went Wrong !!';

const isDefine = (data: any) =>  {
    return data !== null && data !== undefined && data !== NaN;
}
@Injectable()
export class HttpService {

    constructor(
        public http: HttpClient,
        public router: Router
        ) { }

    onHttpStart() {
        // this.barodcaster.broadcast('httpStart');
    }

    onHttpEnd() {
        // this.barodcaster.broadcast('httpEnd');
    }
    post(url: string, data: any) {
        this.onHttpStart();
        return this.http.post(url, data)
        .pipe(map(res =>  this.extractData(res)),
        catchError(res =>  this.handleErrorObservable(res)));

    }

    delete(url: string) {
        this.onHttpStart();
        return this.http.delete(url)
        .pipe(map(res =>  this.extractData(res)),
        catchError(res =>  this.handleErrorObservable(res)));

    }

    get(url: String) {
        this.onHttpStart();
        return this.http.get(url + '')
        .pipe(
            map(res =>  this.extractData(res)),
            catchError(res =>  this.handleErrorObservable(res))
        );
    }

    put(url: string, data: any) {
        this.onHttpStart();
        return this.http.put(url, data)
        .pipe(
            map(res =>  this.extractData(res)),
            catchError(res =>  this.handleErrorObservable(res))
        );
    }


    search(url: string, sortBy: string, sortType: string, page: number, limit: number) {
        this.onHttpStart();
        const pagination = `&sortBy=${sortBy}&sortType=${sortType}&page=${page}&limit=${limit}`;
        return this.get(url + pagination);
    }

    getErrorCode(res) {
        if (res.error !== undefined && res.error !== null) {
              return res.error.status;
        } else if (res.status !== undefined && res.status !== null) {
            return res.status;
        } else {
            return 200;
        }
    }


    private handleErrorObservable (res: Response | any) {
        this.onHttpEnd();
        if (this.getErrorCode(res) === 401) {
             this.router.navigate(['auth/login'], { queryParams:  {error : res.error.message} , skipLocationChange: true});
        }

        if (this.getErrorCode(res) === 404) {
            this.router.navigate(['404']);

        }

        if (this.getErrorCode(res) === 500) {
            this.router.navigate(['500']);
        }

        // this.toast.error(SOMETHING_WENT_WRONG);
        return throwError(res.error || res);
      }

      private extractData(res: Response | any) {
            this.onHttpEnd();
          const body = res;
          if (body && body.status && body.status === 'failed') {
              // this.toast.error(res.msg);
          } else if (body && body.status && body.status === 'success') {
              // this.toast.success(res.msg);
          }
          return body || {};
      }

}