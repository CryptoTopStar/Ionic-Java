import { Component, OnInit, Input, ChangeDetectorRef, OnChanges } from '@angular/core';
import { User } from '../../../shared/models/user.model';
import { MediaChange, MediaObserver } from '@angular/flex-layout';
import { AuthService } from '../../../auth/auth.service';
import {BroadCastService} from '../../../core/services/broadcast.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnChanges {

  loading = true;
  user: User;
  sideNavOpened = true;
  matDrawerOpened = false;
  matDrawerShow = true;
  sideNavMode = 'side';
  @Input() isVisible = true;
  visibility = 'shown';
  isDarkTheme = false;

  constructor(
    private media: MediaObserver,
    public auth: AuthService,
    private broadcaster: BroadCastService,
    private cdRef: ChangeDetectorRef
    ) {

      this.broadcaster.on('httpStart')
      .subscribe(message => {
         this.loading = true;
         this.cdRef.detectChanges();
      });

      this.broadcaster.on('httpEnd')
      .subscribe(message => {
         this.loading = false;
         this.cdRef.detectChanges();
      });

      this.broadcaster.on('themeChange')
      .subscribe(message => {
         this.isDarkTheme = !this.isDarkTheme;
         this.cdRef.detectChanges();
      });
    }

  ngOnInit() {
    this.auth.getUserProfile()
      .subscribe(user => {
          this.user = user;
      });

      this.media.media$.subscribe((change: MediaChange) => {
        this.toggleView();
    });
  }


  ngOnChanges() {
   this.visibility = this.isVisible ? 'shown' : 'hidden';
  }




    getRouteAnimation(outlet) {
        return outlet.activatedRouteData.animation;
       // return outlet.isActivated ? outlet.activatedRoute : ''
    }

  toggleView() {
      if (this.media.isActive('gt-md')) {
            this.sideNavMode = 'side';
            this.sideNavOpened = true;
            this.matDrawerOpened = false;
            this.matDrawerShow = true;
        } else if (this.media.isActive('gt-xs')) {
            this.sideNavMode = 'side';
            this.sideNavOpened = false;
            this.matDrawerOpened = true;
            this.matDrawerShow = true;
        } else if (this.media.isActive('lt-sm')) {
            this.sideNavMode = 'over';
            this.sideNavOpened = false;
            this.matDrawerOpened = false;
            this.matDrawerShow = false;
        }
}

}
