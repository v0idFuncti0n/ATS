export class Education {
  education: string;
  institute: string;
  duration: string;
  location: string;

  constructor(education: string, institute: string, duration: string, location: string) {
    this.education = education;
    this.institute = institute;
    this.duration = duration;
    this.location = location;
  }
}
