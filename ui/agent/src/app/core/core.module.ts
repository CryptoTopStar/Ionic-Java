import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './modules/material.module';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from '../auth/auth.service';
import { JwtModule } from '@auth0/angular-jwt';
import { TokenStorageService } from '../auth/token.storage.service';
import { PageNotFoundComponent, InternalServerErrorComponent } from './components/';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import { CoreService } from './services/core.service';
import { RouterModule } from '@angular/router';
import { ToastService, HttpService } from '../shared/services';
import { BroadCastService } from './services';



const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
    suppressScrollX: true
};


export function tokenGetter() {
    return localStorage.getItem('access_token');
 }

@NgModule({
    declarations: [
        PageNotFoundComponent,
        InternalServerErrorComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        HttpClientModule,
        RouterModule,
        PerfectScrollbarModule,

        JwtModule.forRoot({
            config: {
              tokenGetter: tokenGetter,
              whitelistedDomains: ['localhost:4200'],
              blacklistedRoutes: []
            }
          })
     ],
    exports: [
        MaterialModule,
        HttpClientModule,

        ],
        providers: [
        AuthService,
        CoreService,
        RouterModule,
        ToastService,
        HttpService,
        TokenStorageService,
        BroadCastService,
        {
            provide: PERFECT_SCROLLBAR_CONFIG,
            useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
        }

    ],
})
export class CoreModule {}
