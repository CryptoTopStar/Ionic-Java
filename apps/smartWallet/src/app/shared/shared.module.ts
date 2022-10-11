import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule , FormsModule} from '@angular/forms';
import {AppHeaderComponent,AppFooterComponent} from './components';
import { IonicModule } from '@ionic/angular';
import {  RouterModule } from '@angular/router';
@NgModule({
    declarations: [AppHeaderComponent,AppFooterComponent],
    imports: [ 
        CommonModule,
        IonicModule,
        RouterModule,
        ReactiveFormsModule, 
        FormsModule 
    ],
    exports: [
        AppHeaderComponent,
        AppFooterComponent,
        ReactiveFormsModule, 
        FormsModule],
    providers: [],
})

export class SharedModule {}