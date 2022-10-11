import { Component, OnInit, Input } from '@angular/core';
import { ToolbarHelpers } from './toolbar.helpers';

@Component({
  selector: 'cdk-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit {

  @Input() sidenav;
	@Input() sidebar;
	@Input() drawer;
	@Input() matDrawerShow;
	@Input() currentUser
  
	searchOpen: boolean = false;
  toolbarHelpers = ToolbarHelpers;
	constructor() { }
	
	changeTheme() {
		
	}

  	ngOnInit() {
  	}

}
