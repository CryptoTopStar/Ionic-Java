import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PageNotFoundComponent } from './core/components/page-not-found/page-not-found.component';

const appRoutes: Routes = [
  { path: 'auth',  loadChildren : './auth/auth.module#AuthModule' },
  { path: 'admin',  loadChildren : './admin/admin.module#AdminModule' },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
