import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { AuthRoutingModule } from './auth.routing.module';
import {
  LoginComponent,
  SignupComponent,
  ForgetPasswordComponent,
  SignupSuccessComponent,
  ResetPasswordComponent
 } from './components';
import { CommonModule } from '@angular/common';
import { FormsModule , ReactiveFormsModule} from '@angular/forms';


import { MaterialModule } from '../core/modules/material.module';

@NgModule({
  imports: [
    SharedModule,
    AuthRoutingModule,
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule
  ],
  declarations: [
    LoginComponent,
    SignupComponent,
    ForgetPasswordComponent,
    SignupSuccessComponent,
    ResetPasswordComponent
  ],
  providers: []
})
export class AuthModule {}
