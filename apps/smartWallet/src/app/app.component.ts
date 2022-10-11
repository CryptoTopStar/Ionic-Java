import { Component } from '@angular/core';

import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { BroadCastService, AuthService } from './services';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent {
  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private broadcaster: BroadCastService,
    private navCtrl: NavController,
    private auth: AuthService
  ) {
    this.initializeApp();
  }


  navigateUser() {
    const roles = this.auth.getUserRoles();
    console.log(roles, roles.indexOf('ROLE_ADMIN'));
    if (roles !== undefined && roles.indexOf('ROLE_ADMIN') !== -1) {
      this.navCtrl.navigateRoot('dashboard');
    } else if (roles !== undefined && roles.indexOf('ROLE_USER') !== -1) {
      this.navCtrl.navigateRoot('menu/home');
    }
  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    });

    this.broadcaster.on('loginSuccess')
      .subscribe(message => {
         this.navigateUser();
      });

  }
}
