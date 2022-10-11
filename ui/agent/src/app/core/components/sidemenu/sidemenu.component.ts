import { Component, OnInit, Input } from '@angular/core';
import { adminMenus,userMenus } from './menu-element';
import { Router } from '@angular/router';
import { AuthService } from '../../../auth/auth.service';
@Component({
  selector: 'cdk-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.scss']
})
export class SidemenuComponent implements OnInit {

  constructor(private auth: AuthService, public router : Router) { }

  @Input() iconOnly:boolean = false;
  menus:any;

    
    ngOnInit() {
      let roles = this.auth.getUserRoles();
      if(roles !== undefined && roles.indexOf('ADMIN') !== -1){
        this.menus = adminMenus;
      }else if(roles !== undefined && roles.indexOf('USER') !== -1){
        this.menus = userMenus;
      }
    }

}
