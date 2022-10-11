import { Component, OnInit,Input} from '@angular/core';
import { NavController } from '@ionic/angular';

@Component({
    selector: 'app-category-view',
    templateUrl: './category-view.component.html',
    styleUrls: ['./category-view.component.scss']
})
export class categoryViewComponent implements OnInit {
    
    @Input() data: any;
    constructor(private nav: NavController) { }

    
    ngOnInit(): void { }

    public buttonClick(data: any) {
        this.nav.navigateRoot('/menu/products');
    }
}
