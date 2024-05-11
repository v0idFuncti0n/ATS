export class Test {
  id?: number;
  startDate: string;
  endDate: string;
  candidateNumber: number;
  status: "IN_COMMUNICATION_WITH_CANDIDATES" | "IN_TESTING" | "COMPLETED";

  constructor(id: number, startDate: string, endDate: string, candidateNumber: number, status: "IN_COMMUNICATION_WITH_CANDIDATES" | "IN_TESTING" | "COMPLETED") {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.candidateNumber = candidateNumber;
    this.status = status;
  }
}
