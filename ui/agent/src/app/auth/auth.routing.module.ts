import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import {
  LoginComponent,
  SignupComponent,
  ForgetPasswordComponent,
  SignupSuccessComponent,
  ResetPasswordComponent
} from './components';
@NgModule({
  imports: [
    RouterModule.forChild([
      { path: 'login', component: LoginComponent },
      { path: 'signup', component: SignupComponent },
      { path: 'forget-password', component: ForgetPasswordComponent },
      { path: 'signup-success', component: SignupSuccessComponent },
      { path: 'reset-password', component: ResetPasswordComponent }
    ]),
  ],
  exports: [
    RouterModule,
  ],
})

export class AuthRoutingModule { }
