import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppFooterPage } from './app-footer.page';

describe('AppFooterPage', () => {
  let component: AppFooterPage;
  let fixture: ComponentFixture<AppFooterPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppFooterPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppFooterPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
