import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MaterialModule } from './material.module';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import {
    HomeComponent,
    SearchBarComponent,
    SidebarComponent,
    SidemenuComponent,
    SidemenuItemComponent,
    ToolbarComponent,
    ToolbarNotificationComponent,
    UnauthorizedComponent,
    UserMenuComponent,
 } from '../components';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
    suppressScrollX: true
};

@NgModule({
    declarations: [
        SearchBarComponent,
        SidebarComponent,
        SidemenuComponent,
        SidemenuItemComponent,
        ToolbarComponent,
        ToolbarNotificationComponent,
        UnauthorizedComponent,
        UserMenuComponent,
        HomeComponent
    ],
    imports: [
        CommonModule,
        PerfectScrollbarModule,
        MaterialModule,
        RouterModule
        ],
    exports: [
        PerfectScrollbarModule,
        SearchBarComponent,
        SidebarComponent,
        SidemenuComponent,
        SidemenuItemComponent,
        ToolbarComponent,
        ToolbarNotificationComponent,
        UnauthorizedComponent,
        UserMenuComponent,
        HomeComponent
    ],
    providers: [
        {
            provide: PERFECT_SCROLLBAR_CONFIG,
            useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
        }
    ],
})
export class LayoutModule {}
