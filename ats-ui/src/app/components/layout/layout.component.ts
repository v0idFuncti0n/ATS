import { Component } from '@angular/core';
import {LoginComponent} from "../login/login.component";
import {AuthService} from "../../services/AuthService";
import {Router} from "@angular/router";


@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent {
  constructor( private authService: AuthService, private router: Router) {
  }


  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/');
  }
}
