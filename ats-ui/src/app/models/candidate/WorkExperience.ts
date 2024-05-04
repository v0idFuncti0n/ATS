export class WorkExperience {
  id?: number;
  jobTitle: string;
  company: string;
  location: string;
  duration: string;
  jobSummary: string;

  constructor(id: number, jobTitle: string, company: string, location: string, duration: string, jobSummary: string) {
    this.id = id;
    this.jobTitle = jobTitle;
    this.company = company;
    this.location = location;
    this.duration = duration;
    this.jobSummary = jobSummary;
  }
}
