import { Injectable } from '@angular/core';
import {MediaObserver} from '@angular/flex-layout';

@Injectable()
export class UtilService {
    
    isVisibleOnMobile = false;
    constructor(mediaObserver: MediaObserver) {
        
        mediaObserver.media$.subscribe(media => {
            this.isVisibleOnMobile =  (media.mqAlias === "sm" || media.mqAlias === "xs"); 
          });
    }
    
    
    getDisplayedColumns(displayedColumns): string[] {
        const isMobile = this.isVisibleOnMobile;
        let column = displayedColumns
          .filter(cd => !isMobile || cd.showMobile)
          .map(cd => cd.def);
        return column;
      }
}