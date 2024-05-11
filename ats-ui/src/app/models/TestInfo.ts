import {Candidate} from "./candidate/Candidate";

export class TestInfo {
  id?: number;
  technicalNote: number;
  interviewNote: number;
  finalNote?: number;
  candidate?: Candidate;


  constructor(id: number, technicalNote: number, interviewNote: number, finalNote: number, candidate: Candidate) {
    this.id = id;
    this.technicalNote = technicalNote;
    this.interviewNote = interviewNote;
    this.finalNote = finalNote;
    this.candidate = candidate;
  }
}
