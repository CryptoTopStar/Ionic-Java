import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator, MatTableDataSource, MatSort} from '@angular/material';
import { AfterViewInit } from '@angular/core/src/metadata/lifecycle_hooks';
import { UserService } from '../../user.service';
import {merge} from 'rxjs/observable/merge';
import {startWith} from 'rxjs/operators/startWith';
import {switchMap} from 'rxjs/operators/switchMap';
import {of as observableOf} from 'rxjs/observable/of';
import {catchError} from 'rxjs/operators/catchError';
import {map} from 'rxjs/operators/map';
import { UtilService } from '../../../../../shared/services';

@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit, AfterViewInit {

  isVisibleOnMobile = false;
  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private _service: UserService, public utilService: UtilService) {}

  displayedColumns = [
    {def: 'Name', showMobile: true },
    {def: 'Email', showMobile: true},
    {def: 'Mobile', showMobile: false},
    {def: 'Enable', showMobile: false},
    {def: 'Action', showMobile: true}
  ];
  dataSource = new MatTableDataSource();


  getDisplayedColumns(): string[] {
    return this.utilService.getDisplayedColumns(this.displayedColumns);
  }


  ngOnInit(): void { }

  _loadData() {
    merge(this.sort.sortChange, this.paginator.page)
    .pipe(
      startWith({}),
      switchMap(() => {
        this.isLoadingResults = true;
        return this._service.searchWithPagination(
          this.sort.active, this.sort.direction, this.paginator.pageIndex + 1, this.paginator.pageSize);
      }),
      map(res => {
        this.isLoadingResults = false;
        this.isRateLimitReached = false;
        this.resultsLength = res.count || 0;
        return res.data;
      }), catchError(() => {
        this.isLoadingResults = false;
        return observableOf([]);
      })
    )
    .subscribe(data => {
      this.dataSource.data = data || [];
    });
  }

  ngAfterViewInit() {
    this._loadData();
   }

   onEnableUser(el) {
    this._service.enableUser(el.id, el.enabled)
    .subscribe(data => {
      console.log(data);
    });
   }

   onDelete(el) {
       this._service.deleteUser(el.id)
       .subscribe(data => {
         console.log(data);
       });
   }


}
