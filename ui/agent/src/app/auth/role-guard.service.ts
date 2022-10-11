import { Injectable } from '@angular/core';

import { Router, CanActivate, ActivatedRouteSnapshot } from '@angular/router';
import { AuthService } from './auth.service';
import decode from 'jwt-decode';

@Injectable()
export class RoleGuardService  implements CanActivate {

    constructor(public auth: AuthService, public route: Router) {}

    canActivate(route: ActivatedRouteSnapshot): boolean {

        const token = localStorage.getItem('token');
        const expectedRole = route.data.expectedRole;
        const tokenPayload = decode(token);

        if (!this.auth.isAuthenticated() || tokenPayload.role !== expectedRole) {
            this.route.navigate(['auth/login']);
            return false;
        }
        return true;
    }
}