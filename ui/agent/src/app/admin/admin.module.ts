import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthRoutingModule } from './admin.routing.module';
import { LayoutModule } from '../core/modules/layout.module';
import { SharedModule } from '../shared/shared.module';

@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        AuthRoutingModule,
        LayoutModule,
        SharedModule,
        ReactiveFormsModule
        ],
    exports: [],
    providers: [],
})
export class AdminModule {}
