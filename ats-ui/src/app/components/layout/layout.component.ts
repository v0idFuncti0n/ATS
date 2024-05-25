import {Component, OnInit} from '@angular/core';
import {LoginComponent} from "../login/login.component";
import {AuthService} from "../../services/AuthService";
import {Router} from "@angular/router";
import {User} from "../../models/User";


@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit{

  currentUser: User | undefined;
  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.currentUser = this.authService.getCurrentUser()!;
  }

  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/');
  }
}
