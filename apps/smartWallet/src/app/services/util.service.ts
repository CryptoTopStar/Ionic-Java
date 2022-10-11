import { Injectable } from '@angular/core';


@Injectable()
export class UtilService {
    
    isVisibleOnMobile = false;
    constructor() {}

    getDisplayedColumns(displayedColumns): string[] {
        const isMobile = this.isVisibleOnMobile;
        let column = displayedColumns
          .filter(cd => !isMobile || cd.showMobile)
          .map(cd => cd.def);
        return column;
      }

    disableFormFields(aform: any, disabled: boolean) {
      const state = disabled ? 'disable' : 'enable';
      Object.keys(aform.controls).forEach((controlName) => {
        aform.controls[controlName][state]();
      });
    }
}
