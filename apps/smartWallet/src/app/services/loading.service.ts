import { Injectable } from '@angular/core';
import { LoadingController } from '@ionic/angular';

@Injectable({
    providedIn: 'root'
  })
  export class LoadingService {
  
    private isLoading = false;
    constructor(public loadingController: LoadingController) { }
  
    async present() {
      this.isLoading = true;
      return await this.loadingController.create({
        duration: 5000,
      }).then(a => {
        a.present().then(() => {
          console.log('presented');
          if (!this.isLoading) {
            a.dismiss().then(() => console.log('abort presenting'));
          }
        });
      });
    }
  
    async dismiss() {
      this.isLoading = false;
      return await this.loadingController.dismiss().then(() => console.log('dismissed'));
    }
  }