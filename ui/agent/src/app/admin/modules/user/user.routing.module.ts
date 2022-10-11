import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {
    AddUserComponent,
    UserListComponent
 } from './components';

const userRoutes: Routes = [
    { path: 'add', component: AddUserComponent },
    { path: '', component: UserListComponent }

];

@NgModule({
  imports: [
    RouterModule.forChild(userRoutes),
  ],
  exports: [
    RouterModule,
  ],
})

export class UserRoutingModule { }
