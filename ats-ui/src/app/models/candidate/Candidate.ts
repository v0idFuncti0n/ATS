import {WorkExperience} from "./WorkExperience";
import {Education} from "./Education";
import {Skill} from "./Skill";
import {Language} from "./Language";

export class Candidate {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  location: string;
  birthDate: string;
  verified: boolean;
  resumeFilename: string;
  bootcampId: number;
  status: "IN_POOL" | "IN_TEST" | "IN_BOOTCAMP";
  workExperiences: WorkExperience[];
  educations: Education[];
  skills: Skill[];
  languages: Language[];


  constructor(id: number, firstName: string, lastName: string, email: string, phoneNumber: string, location: string, birthDate: string, verified: boolean,  bootcampId: number, status: "IN_POOL" | "IN_TEST" | "IN_BOOTCAMP", resumeFilename: string, workExperiences: WorkExperience[], educations: Education[], skills: Skill[], languages: Language[]) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.location = location;
    this.birthDate = birthDate;
    this.verified = verified;
    this.bootcampId = bootcampId;
    this.status = status;
    this.resumeFilename = resumeFilename;
    this.workExperiences = workExperiences;
    this.educations = educations;
    this.skills = skills;
    this.languages = languages;
  }
}
