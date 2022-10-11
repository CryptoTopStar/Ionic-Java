import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../core/modules/material.module';
import { Ng2OdometerModule } from 'ng2-odometer';
import {
        AppDashcardComponent,
        NoDataFoundComponent
    } from './components/';
import {

    VlidationErrorMessageService,
    ToastService,
    UtilService,
    HttpService,
    DataStorageService
} from './services';

@NgModule({
    declarations: [
        AppDashcardComponent,
        NoDataFoundComponent
    ],
    imports: [
        CommonModule,
        MaterialModule,
        Ng2OdometerModule
    ],
    exports: [
        AppDashcardComponent,
        MaterialModule,
        Ng2OdometerModule,
        NoDataFoundComponent
    ],
    providers: [
            VlidationErrorMessageService,
            ToastService,
            UtilService,
            HttpService,
            DataStorageService

    ],
})
export class SharedModule {}
