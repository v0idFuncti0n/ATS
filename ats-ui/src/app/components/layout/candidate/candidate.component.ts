import {Component, OnInit, ViewChild} from '@angular/core';
import {Config} from "datatables.net";
import {Candidate} from "../../../models/candidate/Candidate";
import {CandidateService} from "../../../services/CandidateService";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ModalComponent, ModalConfig} from "../modal/modal.component";

@Component({
  selector: 'app-candidate',
  templateUrl: './candidate.component.html',
  styleUrls: ['./candidate.component.css']
})
export class CandidateComponent implements OnInit {
  isLoading = true;
  candidates: Candidate[] = [];
  dtOptions: Config = {};
  candidateForm: FormGroup;
  pdfToDisplayURL: string = "";
  candidateModalConfig: ModalConfig = {
    modalTitle: "Verify Candidate",
    closeButtonLabel: "Verify",
    dismissButtonLabel: "Close",
    onClose: this.verifyCandidate.bind(this)
  }
  @ViewChild('candidateModal') private candidateModalComponent!: ModalComponent

  constructor(private candidateService: CandidateService, private form: FormBuilder) {
    this.candidateForm = this.form.group([]);
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      order: [[5, "asc"]],
      columns: [
        {},
        {},
        {},
        {},
        {},
        {},
        {visible: false},
        {},
        {}
      ]
    };

    this.candidateService.getAllCandidates().subscribe((candidates) => {
      this.candidates = candidates;
      console.log(candidates)
      this.isLoading = false;
    });
  }

  async openModalVerifyCandidate(candidate: Candidate) {
    this.candidateService.getCandidateResumeFile(candidate.id!).subscribe(data => {
      let blob = new Blob([data], {type: 'application/pdf'});
      this.pdfToDisplayURL = window.URL.createObjectURL(blob);
    });
    return await this.candidateModalComponent.open({centered: true, size: "xl", fullscreen: true});
  }

  private verifyCandidate() {
    return true;
  }
}

