import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Test} from "../models/Test";
import {API} from "../api/API";

@Injectable({
  providedIn: "root"
})
export class TestService {
  constructor(private http: HttpClient) {}

  public createTest(test: Test, bootcampId: number) {
    return this.http.post<Test>(API.TEST + '/' + bootcampId, test);
  }

  public changeTestStatusToInTesting(testId: number) {
    return this.http.post<Test>(API.TEST + '/' + testId + '/inTesting', {});
  }

  public changeTestStatusToTestCompleted(testId: number) {
    return this.http.post<Test>(API.TEST + '/' + testId + '/completeTest', {});
  }
}