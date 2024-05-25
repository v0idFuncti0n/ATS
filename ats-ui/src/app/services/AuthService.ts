import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {User} from "../models/User";
import {JwtHelperService} from "@auth0/angular-jwt";
import {API} from "../api/API";


@Injectable()
export class AuthService {
  constructor(private http: HttpClient, public jwtHelper: JwtHelperService) {
  }

  static tokenGetter() {
    return localStorage.getItem('token');
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');    // Check whether the token is expired and return
    // true or false
    return !this.jwtHelper.isTokenExpired(token);
  }

  login(username: string, password: string) {
    return this.http.post<User>(API.AUTHENTICATE, {username, password})
  }

  getCurrentUser() {
    if (this.isAuthenticated()) {
      return new User(
        localStorage.getItem('username')!,
        localStorage.getItem('role')!,
        localStorage.getItem('token')!
      )
    } else {
      return null;
    }
  }

  logout() {
    localStorage.removeItem('username');
    localStorage.removeItem('token');
    localStorage.removeItem('role');
  }
}
