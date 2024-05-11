import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Test} from "../models/Test";
import {API} from "../api/API";
import {Candidate} from "../models/candidate/Candidate";
import {TestInfo} from "../models/TestInfo";

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

  public getAllTests() {
    return this.http.get<Test[]>(API.TEST);
  }

  getTestById(testId: number) {
    return this.http.get<Test>(API.TEST + '/' + testId);
  }
}
