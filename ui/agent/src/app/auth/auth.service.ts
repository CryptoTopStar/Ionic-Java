import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthConstant } from './auth.constant';
import {Observable} from 'rxjs';
import { TokenStorageService } from './token.storage.service';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import 'rxjs/add/observable/throw';
import { HttpService } from '../shared/services/http.service';


@Injectable()
export class AuthService {

    constructor(
        public jwtHelper: JwtHelperService,
        public tokenService: TokenStorageService,
        public router: Router,
        private http: HttpService,
    ) { }

    public isAuthenticated() {
        const token = this.tokenService.getToken();
        return !this.jwtHelper.isTokenExpired(token);
    }

    public attemptAuth(ussername: string, password: string): Observable<any> {
        const credentials = {username: ussername, password: password};
        return this.http.post(AuthConstant.generateTokenUrl, credentials);
    }

    public signup(user: any): Observable<any> {
        const u = {...user};
        const checksum = u.passwords && u.passwords.password;
        if (checksum === u.passwords.confirmPassword) {
            delete u.passwords;
            u.password = checksum;
            return this.http.post(AuthConstant.signup, u);
        }
    }

    public forgetPassword(user): Observable<any> {
        return this.http.put(AuthConstant.forgetPassword, user);
    }

    public resetPassword(user, token): Observable<any> {
        if (token !== null && token !== undefined) {
            const url = `${AuthConstant.resetPassword}?token=${token}`;
            return this.http.put(url, user);
        }
    }

    public logout() {
      this.tokenService.signOut();
      this.router.navigate(['auth/login']);

    }

    public getUserRoles() {
        if (this.isAuthenticated()) {
            const token = this.tokenService.getToken();
            const decodedToken = this.jwtHelper.decodeToken(token);
            if (decodedToken.scopes !== null && decodedToken.scopes !== undefined) {
                 return decodedToken.scopes;
            }
        }
    }

    public getUserProfile(): any {
        return this.http.get(AuthConstant.userProfile)
        .pipe(map(user => {
            return user;
        }));
    }
}
