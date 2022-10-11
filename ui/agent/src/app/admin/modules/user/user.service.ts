import { Injectable } from '@angular/core';
import { UserUrl } from '../../../shared/constants/app.url';

import { HttpService } from '../../../shared/services/http.service';
import { map } from 'rxjs/operators';



@Injectable()
export class UserService {

    constructor(private http: HttpService) {}

    getAllRoles() {
        return this.http.get(UserUrl.getAllRoles)
        .pipe(map(roles => {
            return roles;

        }));
    }

    findAll() {
        return this.http.get(UserUrl.getAllUsers)
        .pipe(map(users => {
            return users;
        }));
    }

    searchWithPagination(sortBy: string, sortType: string, page: number, limit: number) {
        let url = UserUrl.search;
        url += `?q=deleted==false`;
        return this.http.search(url, sortBy, sortType, page, limit)
        .pipe(map(res => {
            return res;
        }));

    }

    enableUser(userId, enabled) {
        let url = UserUrl.enableUser;
        url += `/${userId}/${enabled}`;
        return this.http.post(url, '')
        .pipe(map(res => {
            return res;
        }));
    }

    createUser(user: any) {
        const newRoles = [{id: user.roles}];
        const userItem = {...user};
        userItem.roles = newRoles;
        return this.http.post(UserUrl.addUser, userItem);
    }

    deleteUser(userId) {
        let url = UserUrl.deleteUser;
        url += `${userId}`;
        return this.http.delete(url)
        .pipe(map(res => {
            return res;
        }));
    }
}
