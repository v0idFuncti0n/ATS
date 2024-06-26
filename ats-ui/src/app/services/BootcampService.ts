import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Bootcamp} from "../models/Bootcamp";
import {Observable} from "rxjs";
import {API} from "../api/API";

@Injectable({
  providedIn: "root"
})
export class BootcampService {
  constructor(private http: HttpClient) {}

  public getAllBootcamps(): Observable<Bootcamp[]> {
    return this.http.get<Bootcamp[]>(API.BOOTCAMPS);
  }

  public createBootcamp(bootcamp: Bootcamp): Observable<Bootcamp> {
    return this.http.post<Bootcamp>(API.BOOTCAMP, bootcamp);
  }

  updateBootcamp(bootcampToUpdate: Bootcamp, bootcampId: number): Observable<Bootcamp> {
    return this.http.put<Bootcamp>(API.BOOTCAMPS + "/" + bootcampId, bootcampToUpdate);
  }

  deleteBootcamp(bootcampId: number): Observable<Bootcamp> {
    return this.http.delete<Bootcamp>(API.BOOTCAMPS + "/" + bootcampId);
  }
}
