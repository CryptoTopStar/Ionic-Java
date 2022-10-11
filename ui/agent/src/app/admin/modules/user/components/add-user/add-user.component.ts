import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { VlidationErrorMessageService } from '../../../../../shared/services';
import {MediaObserver} from '@angular/flex-layout';
import { Router } from '@angular/router';
import { UserService } from '../../user.service';

@Component({
    selector: 'app-add-user',
    templateUrl: './add-user.component.html',
    styleUrls: ['./add-user.component.scss']
})
export class AddUserComponent implements OnInit {

    isVisibleOnMobile = true;
    public userForm: FormGroup;
    disabled = false;
    submitted = false;
    roleList: [];

    constructor(public form: FormBuilder,
        public userService: UserService,
        public mediaObserver: MediaObserver,
        private errMsgSrv: VlidationErrorMessageService,
        private router: Router
        )
        {
            this.userForm = this.form.group({
            username: ['', { validators: [Validators.minLength(3), Validators.required], updateOn: 'blur'}],
            email: ['', Validators.required],
            mobile: [ '', {validators: [Validators.required, Validators.minLength(10),Validators.maxLength(10)], updateOn: 'blur'}],
            password: ['', Validators.required],
            lastName: ['', {validators: '', updateOn: 'blur'}],
            roles: ['', Validators.compose([Validators.required])],
            });

        mediaObserver.media$.subscribe(media => {
           this.isVisibleOnMobile =  (media.mqAlias === 'md');
        });
    }

    get username() { return this.userForm.get('username'); }
    get password() { return this.userForm.get('password'); }
    get email() {  return this.userForm.get('email'); }
    get mobile() {  return this.userForm.get('mobile'); }
    get lastName() {  return this.userForm.get('lastName'); }
    get roles() {  return this.userForm.get('roles'); }

    getUserRoles() {
        this.userService.getAllRoles()
        .subscribe(data => {
            this.roleList = data;
        });
    }

    addUser(user) {
        this.userService.createUser(user)
        .subscribe(data => {
            if ( data.status && data.status === 'success') {
                this.goBack();
            }
        });
    }

    ngOnInit(): void {
        this.getUserRoles();
     }

     goBack() {
        this.router.navigate(['admin/user']);
     }
}
