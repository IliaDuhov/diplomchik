import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  form = this.fb.group({
  username: this.fb.control('', [Validators.required, Validators.minLength(3)]),
  password: this.fb.control('', [Validators.required, Validators.minLength(6)]),
});



  constructor(private fb: FormBuilder, private router: Router) {}

  submit() {
    if (this.form.invalid) return;
    localStorage.setItem('isRegistered', 'true');
    this.router.navigate(['/']);
  }
}
