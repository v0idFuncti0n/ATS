import { Component } from '@angular/core';
import {Bootcamp} from "../../../models/Bootcamp";
import {BootcampService} from "../../../services/BootcampService";
import {CandidateService} from "../../../services/CandidateService";
import {Candidate} from "../../../models/candidate/Candidate";
import {TestService} from "../../../services/TestService";

@Component({
  selector: 'app-bootcamp',
  templateUrl: './bootcamp.component.html',
  styleUrls: ['./bootcamp.component.css']
})
export class BootcampComponent {
  bootcamps: Bootcamp[] = [];

  constructor(bootcampService: BootcampService, cs: CandidateService, ts: TestService) {
    bootcampService.getAllBootcamps().subscribe((bootcamps) => {
      this.bootcamps = bootcamps;
      console.log(this.bootcamps);
    });

    cs.getAllCandidates().subscribe(candidates => {
      console.log(candidates);
    })
    let c: Candidate;
    cs.getCandidateById(1).subscribe(candidate => {
      c = candidate;
      console.log(candidate);
      c.skills = [
        {skill: 'Vue.js'},
        {skill: 'Gaming'}
      ]

      cs.updateCandidate(c, 1).subscribe(candidate => {
        console.log(candidate);
      })

      bootcampService.createBootcamp({
        "name": "java",
        "startDate": "2024-04-20",
        "endDate": "2024-04-21",
        "candidateNumber": 20,
        "skillRequired": "java",
        "languageRequired": "english",
        "languageLevelRequired": "B1"
      }).subscribe(bootcamp => {
        console.log("bootcamp")
        console.log(bootcamp)
        ts.createTest({
          "startDate": "2024-04-20",
          "endDate": "2024-04-21",
          "candidateNumber": 100
        }, bootcamp.id!).subscribe(test => {
          console.log("test")
          console.log(test);
        })
      })
    });


    cs.getCandidateResumeFile(1).subscribe(resumeFile => {
      let downloadURL = URL.createObjectURL(resumeFile);
      console.log(resumeFile);
    })
  }

}
