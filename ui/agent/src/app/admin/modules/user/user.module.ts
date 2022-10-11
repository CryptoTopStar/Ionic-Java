import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../shared/shared.module';
import { AddUserComponent, UserListComponent} from './components';
import { UserRoutingModule } from './user.routing.module';
import { UserService } from './user.service';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
    declarations: [
        UserListComponent,
        AddUserComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        UserRoutingModule,
        ReactiveFormsModule
    ],
    exports: [],
    providers: [UserService],
})
export class UserModule {}
