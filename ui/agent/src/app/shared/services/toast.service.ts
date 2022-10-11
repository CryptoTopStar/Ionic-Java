import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material';

@Injectable()
export class ToastService {

  public constructor(private snackBar: MatSnackBar) {}

   public simple(message: string, showCloseButton = false, duration = 6000): MatSnackBarRef<SimpleSnackBar> {
    const ref = this.snackBar.open(
      message,
      showCloseButton ? 'close' : null,
      { duration: duration }
    );
    ref.onAction().subscribe(() => {});

    return ref;
  }

  public error(message: string, showCloseButton = false, duration = 6000): MatSnackBarRef<SimpleSnackBar> {
    const ref = this.snackBar.open(
      message,
      showCloseButton ? 'close' : null,
      {
        duration: duration,
        panelClass : ['red-snackbar']
       }
    );
    ref.onAction().subscribe(() => {});

    return ref;
  }

  public success(message: string, showCloseButton = false, duration = 6000): MatSnackBarRef<SimpleSnackBar> {
    const ref = this.snackBar.open(
      message,
      showCloseButton ? 'close' : null,
      {
        duration: duration,
        panelClass : ['green-snackbar']
       }
    );
    ref.onAction().subscribe(() => {});

    return ref;
  }
}
