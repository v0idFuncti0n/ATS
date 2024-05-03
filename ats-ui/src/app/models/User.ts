export class User {
  username: string;
  role: string;
  token: string;


  constructor(username: string, role: string, token: string) {
    this.username = username;
    this.role = role;
    this.token = token;
  }
}
