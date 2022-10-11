import {Injectable} from '@angular/core';

@Injectable()

export class VlidationErrorMessageService {
  
    constructor() {}

  getFormErrorMessage(field) {
    return field.hasError('required') ? 'this Field is Required.' :
    field.hasError('email') ? 'Not a valid email. ' :
    field.hasError('minlength') ? 'Minimum lenght required. ' :
    field.hasError('maxlength') ? 'Char can not be more than max lenght.' :
    field.hasError('passwordMismatch') ? 'Password didt not match' : '';
  }

}
