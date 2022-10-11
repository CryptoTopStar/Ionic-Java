import { Component, OnInit } from '@angular/core';
import { NavController, AlertController } from '@ionic/angular';
import { AuthService } from '../../services';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

  createSuccess = false;
  registerCredentials = { username: '', email: '', password: '', confirmPassword: '' };
  constructor(
    private nav: NavController,
    private auth: AuthService,
    private alertCtrl: AlertController
  ) { }

  ngOnInit() {
  }

  public register(registerCredentials) {
    
    if (registerCredentials.password != registerCredentials.confirmPassword) {
      this.showPopup("Error", 'The password confirmation does not match.');
    } else {
      this.auth.signup(registerCredentials).subscribe(success => {
        if (success) {
          this.createSuccess = true;
          this.showPopup("Success", "Account created.");
        } else {
          this.showPopup("Error", "Problem creating account.");
        }
      },
        error => {
          this.showPopup("Error", error);
        });
    }
  }

  async showPopup(title, text) {
    
    const alert = await this.alertCtrl.create({
      header: title,
      subHeader: '',
      message: text,
      buttons: ['OK']
    });
     await alert.present(); 
}


}
