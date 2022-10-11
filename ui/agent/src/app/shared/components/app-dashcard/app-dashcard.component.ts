import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'app-dashcard',
    templateUrl: './app-dashcard.component.html',
    styleUrls: ['./app-dashcard.component.scss']
})
export class AppDashcardComponent implements OnInit {
    
    constructor() { }

    ngOnInit(): void { }

    @Input() dashData: any;
}
