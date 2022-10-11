import { Component, OnInit } from '@angular/core';
import { User } from '../../../shared/models/user.model';
import { AuthService } from '../../auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Validators, FormBuilder, FormGroup, AbstractControl } from '@angular/forms';
import { VlidationErrorMessageService } from '../../../shared/services';


@Component({
    selector: 'app-forget-password',
    templateUrl: './forget-password.component.html',
    styleUrls: ['./forget-password.component.scss']
})

export class ForgetPasswordComponent implements OnInit {

    error: string;
    success: string;
    forgetPwdForm: FormGroup;
    disabled: boolean;

    constructor(public auth: AuthService,

        public router: Router,
        public route: ActivatedRoute,
        public form: FormBuilder,
        private errMsgSrv: VlidationErrorMessageService,

    ) {
        this.forgetPwdForm = this.form.group({
            email: ['', { validators: [Validators.required, Validators.email]}],
            // mobile: ['', { validators: [Validators.required, Validators.maxLength(10), Validators.minLength(10)]}],
        });
    }


    get email() { return this.forgetPwdForm.get('email'); }
    // get mobile() { return this.forgetPwdForm.get('mobile'); }


    ngOnInit(): void {}

    resetPassword(user: User) {
        this.auth.forgetPassword(user)
        .subscribe(
            data => {
                if (data.status && data.status === 'success') {
                    this.success = data.msg;
                    this.error = '';
                } else {
                    this.error = data.msg;
                    this.success='';
                }

            }
          );
    }

}
