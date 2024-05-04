import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TestInfo} from "../models/TestInfo";
import {API} from "../api/API";

@Injectable({
  providedIn: "root"
})
export class TestInfoService {

  constructor(private http: HttpClient) {}

  public updateTestInfo(testInfo: TestInfo, testInfoId: number) {
    return this.http.post<TestInfo>(API.TEST_INFO + '/' + testInfoId, testInfo)
  }
}
