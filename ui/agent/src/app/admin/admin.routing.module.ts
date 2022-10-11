import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from '../core/components/home/home.component';


  const appRoutes: Routes = [
    { path: '', component: HomeComponent, children: [
    { path: 'user', loadChildren : './modules/user/user.module#UserModule'}
    ]}
];

@NgModule({
  imports: [
    RouterModule.forChild(appRoutes),
  ],
  exports: [
    RouterModule,
  ],
})

export class AuthRoutingModule { }
