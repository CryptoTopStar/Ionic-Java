import { Component, OnInit } from '@angular/core';
import {footerItems} from './footer-item';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-footer',
  templateUrl: './app-footer.component.html',
  styleUrls: ['./app-footer.component.scss'],
})
export class AppFooterComponent implements OnInit {

  public footers = footerItems;
  constructor(private nav: NavController) {}

  public navigateRoute(url:string) {
    this.nav.navigateRoot(url);
}
  ngOnInit() {}

}
