import {Test} from "./Test";

export class Bootcamp {
  id?: number;
  name: string;
  startDate: string;
  endDate: string;
  candidateNumber: number;
  skillRequired: string;
  languageRequired: string;
  languageLevelRequired: string;
  test: Test;

  constructor(id: number, name: string, startDate: string, endDate: string, candidateNumber: number, skillRequired: string, languageRequired: string, languageLevelRequired: string, test: Test) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.candidateNumber = candidateNumber;
    this.skillRequired = skillRequired;
    this.languageRequired = languageRequired;
    this.languageLevelRequired = languageLevelRequired;
    this.test = test;
  }
}
