export class TestInfo {
  id?: number;
  technicalNote: number;
  interviewNote: number;
  finalNote?: number;


  constructor(id: number, technicalNote: number, interviewNote: number, finalNote: number) {
    this.id = id;
    this.technicalNote = technicalNote;
    this.interviewNote = interviewNote;
    this.finalNote = finalNote;
  }
}
