import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from './auth/auth.service';
import {BroadCastService} from './core/services/broadcast.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{


  constructor(
    public router: Router,
    private auth: AuthService,
    public route: ActivatedRoute,
    private braodcaster: BroadCastService
    ) {
      this.braodcaster.on('loginSuccess')
      .subscribe(message => {
         this.navigateUser();
      });
   }

  title = 'SmartIOT';

  navigateUser() {
    const roles = this.auth.getUserRoles();
    if (roles !== undefined && roles.indexOf('ADMIN') !== -1) {
        this.router.navigate(['admin/user']);
    } else if (location.href.indexOf('reset-password') === -1) {
       this.router.navigate(['auth/login']);
    }
  }
  ngOnInit(): void {
    this.navigateUser();
  }
}


