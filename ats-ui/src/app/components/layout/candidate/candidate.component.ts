import {Component, OnInit, ViewChild} from '@angular/core';
import {Config} from "datatables.net";
import {Candidate} from "../../../models/candidate/Candidate";
import {CandidateService} from "../../../services/CandidateService";

@Component({
  selector: 'app-candidate',
  templateUrl: './candidate.component.html',
  styleUrls: ['./candidate.component.css']
})
export class CandidateComponent implements OnInit {

  isLoading = true;
  candidates: Candidate[] = [];
  dtOptions: Config = {};

  constructor(private candidateService: CandidateService) {
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
    };

    this.candidateService.getAllCandidates().subscribe((candidates) => {
      this.candidates = candidates;
      console.log(candidates)
      this.isLoading = false;
    });
  }
}

