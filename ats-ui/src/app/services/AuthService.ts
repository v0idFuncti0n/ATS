import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {AuthAPI} from "../api/AuthAPI";
import {User} from "../models/User";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable()
export class AuthService {
  constructor(private http: HttpClient, public jwtHelper: JwtHelperService) {}
  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');    // Check whether the token is expired and return
    // true or false
    return !this.jwtHelper.isTokenExpired(token);
  }

  login(username: string, password: string) {
    return this.http.post<User>(AuthAPI.AUTHENTICATE, {username, password})
  }

  static tokenGetter() {
    return localStorage.getItem('token');
  }
}
