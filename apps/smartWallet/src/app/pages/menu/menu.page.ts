import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services';
import menu from './menu.list';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.page.html',
  styleUrls: ['./menu.page.scss'],
})
export class MenuPage implements OnInit {

  pages = menu;
  constructor(
    private auth: AuthService,
    ) { }

  ngOnInit() {
  }

  public logout() {
    this.auth.logout();
  }

  

}
