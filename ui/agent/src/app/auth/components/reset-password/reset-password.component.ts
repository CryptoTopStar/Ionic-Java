import { Component, OnInit } from '@angular/core';
import { User } from '../../../shared/models/user.model';
import { AuthService } from '../../auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Validators, FormBuilder, FormGroup, AbstractControl } from '@angular/forms';
import { VlidationErrorMessageService } from '../../../shared/services';


@Component({
    selector: 'app-reset-password',
    templateUrl: './reset-password.component.html',
    styleUrls: ['./reset-password.component.scss']
})

export class ResetPasswordComponent{

    error: string;
    success: string;
    resetPwdForm: FormGroup;
    token: string;
    disabled: boolean;

    constructor(public auth: AuthService,

        public router: Router,
        public route: ActivatedRoute,
        public form: FormBuilder,
        private errMsgSrv: VlidationErrorMessageService,

    ) {
        this.resetPwdForm = this.form.group({
            password: ['', { validators: [Validators.required]}],
            confirmPassword: ['', { validators: [Validators.required]}]
        });

        this.route.queryParams.subscribe(params => {
            this.token = params['token'];
            console.log('token::', this.token);
        });
    }


    get password() { return this.resetPwdForm.get('password'); }
    get confirmPassword() { return this.resetPwdForm.get('confirmPassword'); }


    resetPassword(user: User) {
        this.auth.resetPassword(user, this.token)
        .subscribe(
            data => {
                if (data.status && data.status === 'success') {
                    this.success = data.msg;
                } else {
                    this.error = data.msg;
                }

            }
          );
    }

}
