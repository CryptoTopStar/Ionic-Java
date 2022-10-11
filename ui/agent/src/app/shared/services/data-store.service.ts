import { Injectable } from '@angular/core';


@Injectable()
export class DataStorageService {


    private store;
    public constructor() {
        this.store = {};
     }
    setData = (data: any) => {
        const keys = Object.keys(data);
        keys.forEach(key => {
            this.store[key] = data[key];
        });
    }

    getData = (key) => {
        return this.store[key];
    }


}
