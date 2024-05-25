import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/AuthService";
import {Router} from "@angular/router";
import { Validator} from "@angular/forms";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
     form: FormGroup ;



  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private toastr: ToastrService)
{

    this.form = this.fb.group({
      username: ['',Validators.required],
      password: ['',Validators.required]
    });
  }

  login() {
    const val = this.form.value;
    console.log(val)
    if(val.username !== null){
      console.log(val);
    }
    if (val.username && val.password) {
      this.authService.login(val.username, val.password)
        .subscribe(
          (user) => {
            localStorage.setItem("username", user.username);
            localStorage.setItem("role", user.role);
            localStorage.setItem("token", user.token);
            this.toastr.success("login successfully !");
            this.router.navigateByUrl('/');

          },
          (err) => {
            this.toastr.error("invalid login info!");
            console.log(err);
          }
        );
    }
  }

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/');

  }
}
