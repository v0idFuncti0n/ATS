export class Test {
  id?: number;
  startDate: string;
  endDate: string;
  candidateNumber: number;

  constructor(id: number, startDate: string, endDate: string, candidateNumber: number) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.candidateNumber = candidateNumber;
  }
}
