import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit {

  profileForm;
  submitted=false;
  
  constructor(private fb: FormBuilder) { 
    
    this.profileForm = this.fb.group({
      city: ['',Validators.required],
      address: ['', Validators.required],
      email: ['', { validators: [Validators.required,Validators.email]}]
      });
    
  }

  get city() { return this.profileForm.get('city'); }
  get address() { return this.profileForm.get('address'); }
  get email() { return this.profileForm.get('email'); }

  ngOnInit() {
  }

  onSubmit(value) {
   console.log('form values',value);
  }

}
