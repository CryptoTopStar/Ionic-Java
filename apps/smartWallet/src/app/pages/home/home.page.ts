import { Component } from '@angular/core';
import { AuthService, HttpService } from '../../services';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  catList;

  constructor(
    private auth: AuthService, 
    public http:HttpService) {
    this.loadData();
  }

  loadData() {}


 
}
