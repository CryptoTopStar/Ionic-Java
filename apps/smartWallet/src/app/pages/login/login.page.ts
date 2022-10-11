import { Component, OnInit } from '@angular/core';
import {AlertController, NavController } from '@ionic/angular';
import {AuthService, LoadingService, TokenStorageService, BroadCastService} from '../../services';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  loginForm: FormGroup;
  loginErrorText;
  constructor(
    private auth: AuthService,
    private alertCtrl: AlertController,
    private navCtrl: NavController,
    private loading: LoadingService,
    private token: TokenStorageService,
    private broadcaster: BroadCastService,
    public form: FormBuilder,
    ) {
      this.loginForm = this.form.group({
        username: ['', { validators: [
          Validators.required,
          Validators.minLength(10),
          Validators.maxLength(10)
        ]}],
        password: ['', { validators: [Validators.required]}]
      });
     }

  get username() { return this.loginForm.get('username'); }
  get password() { return this.loginForm.get('password'); }
  ngOnInit() {
  }

  public createAccount() {
    this.navCtrl.navigateRoot('/register');
  }
  
  public login(user :any) {
    this.loading.present()
    this.auth.attemptAuth(user.username,user.password)
    .subscribe(data => {
      if (data && data.token) {
        this.token.saveToken(data.token);
        this.broadcaster.broadcast('loginSuccess', true);
      } else {
        this.loginErrorText = "These credentials do not match our records.";
      }
    },
      error => {
        this.loginErrorText = error;
      });
  }

}
