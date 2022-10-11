import { Component, OnInit, Input, HostListener, ElementRef } from '@angular/core';
import { AuthService } from '../../../auth/auth.service';
import {BroadCastService} from '../../../core/services/broadcast.service';

@Component({
  selector: 'cdk-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.scss']
})
export class UserMenuComponent implements OnInit {

 isOpen = false;
 isDarkTheme = false;
 userMenuList = [
  {'name': 'Profile', 'action': 'profile', 'icon': 'zmdi-account-circle'},
  {'name': 'Settings', 'action': 'setting', 'icon': 'zmdi-settings'},
  {'name': 'Log Out', 'action': 'logout', 'icon': 'zmdi-sign-in'}
];
@Input() currentUser = null;

 constructor(
  private elementRef: ElementRef,
  private auth: AuthService,
  private broadcast: BroadCastService
 ) { }

ngOnInit() {}
@HostListener('document:click', ['$event', '$event.target'])

onClick(event: MouseEvent, targetElement: HTMLElement) {
    if (!targetElement) { return; }
		const clickedInside = this.elementRef.nativeElement.contains(targetElement);
    	if (!clickedInside) {
      		this.isOpen = false;
    	}
  	}
  	
    logout(){
		this.auth.logout();
		}
		
		changeTheme() {
			this.isDarkTheme = !this.isDarkTheme;
			this.broadcast.broadcast('themeChange');
		}
   
	userAction(action){
		  switch(action){
		   case 'logout':
			   this.logout();
				 break;
			 case 'theme':
					this.changeTheme();
					break;

			 
		  }
	  }

}
