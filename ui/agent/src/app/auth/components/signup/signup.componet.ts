import { Component, OnInit } from '@angular/core';
import { User } from '../../../shared/models/user.model';
import { AuthService } from '../../auth.service';
import { TokenStorageService } from '../../token.storage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Validators, FormBuilder, FormGroup, AbstractControl } from '@angular/forms';
import { VlidationErrorMessageService } from '../../../shared/services';


@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.scss']
})

export class SignupComponent implements OnInit {

    error: string;
    signupForm: FormGroup;
    disabled: boolean;

    constructor(public auth: AuthService,
        public token: TokenStorageService,
        public router: Router,
        public route: ActivatedRoute,
        public form: FormBuilder,
        private errMsgSrv: VlidationErrorMessageService,

    ) {
        this.signupForm = this.form.group({
            username: ['', { validators: [Validators.required]}],
            passwords: this.form.group({
                password: ['', { validators: [Validators.required]}],
                confirmPassword: ['', { validators: [Validators.required]}]
            }, { validator: this.passwordConfirming}),
            email: ['', { validators: [Validators.required, Validators.email]}],
            mobile: ['', { validators: [Validators.required, Validators.maxLength(10), Validators.minLength(10)]}],
        });
    }

    get username() { return this.signupForm.get('username'); }
    get password() { return this.signupForm.get(['passwords', 'password']); }
    get confirmPassword() { return this.signupForm.get(['passwords', 'confirmPassword']); }
    get email() { return this.signupForm.get('email'); }
    get mobile() { return this.signupForm.get('mobile'); }

    passwordConfirming(c: AbstractControl) {
        
        if (c.get('password').value !== c.get('confirmPassword').value) {
            return ({invalid: true});
        }
    }
    ngOnInit(): void {
        this.route.queryParams.subscribe(params => {
           this.error = params['error'];
        });
    }

    signup(user: User) {
        this.auth.signup(user)
        .subscribe(
            data => {
                if (data.status && data.status === 'success') {
                    this.router.navigate(['auth/signup-success']);
                } else {
                    this.error = data.msg;
                }

            }
          );
    }

}
