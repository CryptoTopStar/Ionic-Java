import { Injectable } from '@angular/core';

import { Router, CanActivate } from '@angular/router';
import { AuthService } from './auth.service';


@Injectable()
export class AuthGuardService  implements CanActivate {

    constructor(public auth: AuthService, public route: Router) {}

    canActivate(): boolean {

        const token = localStorage.getItem('token');

        if (!this.auth.isAuthenticated()) {
            this.route.navigate(['auth/login']);
            return false;
        }
        return true;
    }
}