import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { User } from '../../../shared/models/user.model';
import { AuthService } from '../../../auth/auth.service';
import { TokenStorageService } from '../../../auth/token.storage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { VlidationErrorMessageService } from '../../../shared/services';
import {BroadCastService} from '../../../core/services/broadcast.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    user: User = new User();
    error: string;
    loginForm: FormGroup;
    disabled: boolean;

    constructor(
        public auth: AuthService,
        public token: TokenStorageService,
        public router: Router,
        public route: ActivatedRoute,
        public form: FormBuilder,
        private errMsgSrv: VlidationErrorMessageService,
        private broadcaster: BroadCastService

    ) {
        this.loginForm = this.form.group({
            username: ['', { validators: [Validators.required]}],
            password: ['', { validators: [Validators.required]}]
        });
    }

    get username() { return this.loginForm.get('username'); }
    get password() { return this.loginForm.get('password'); }

    ngOnInit(): void {
        this.route.queryParams.subscribe(params => {
           this.error = params['error'];
          });
    }

    login(user: User) {
        this.auth.attemptAuth(user.username, user.password)
        .subscribe(
            data => {
              this.token.saveToken(data.token);
              this.broadcaster.broadcast('loginSuccess', true);
            }
          );
    }

}
