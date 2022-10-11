import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppHeaderPage } from './app-header.page';

describe('AppHeaderPage', () => {
  let component: AppHeaderPage;
  let fixture: ComponentFixture<AppHeaderPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppHeaderPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppHeaderPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
