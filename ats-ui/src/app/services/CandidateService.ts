import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Candidate} from "../models/candidate/Candidate";
import {API} from "../api/API";

@Injectable({
  providedIn: "root"
})
export class CandidateService {
  constructor(private http: HttpClient) {}

  public getAllCandidates() {
    return this.http.get<Candidate[]>(API.CANDIDATES);
  }

  public getCandidateById(id: number) {
    return this.http.get<Candidate>(API.CANDIDATES + '/' + id);
  }

  public getCandidateResumeFile(id: number) {
    const headers = new HttpHeaders();
    return this.http.get<Blob>(API.CANDIDATES + '/' + id + '/resume', {headers, responseType: 'blob' as 'json'});
  }

  public updateCandidate(candidate: Candidate, id: number){
    return this.http.post<Candidate>(API.CANDIDATES + '/' + id + '/verify', candidate);
  }

  public refuseCandidateInTest(candidateId: number, testId: number) {
    return this.http.post<Candidate>(API.CANDIDATES + '/' + candidateId + '/' + 'test/' + testId + '/refuse', {});
  }

  deleteCandidate(candidateId: number) {
    return this.http.delete(API.CANDIDATES + '/' + candidateId);
  }
}
