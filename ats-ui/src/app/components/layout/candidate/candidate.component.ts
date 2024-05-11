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
  @ViewChild('candidateModal') private candidateModalComponent!: ModalComponent


  isLoading = true;
  candidates: Candidate[] = [];
  dtOptions: Config = {};

  candidateForm: FormGroup;

  candidateModalConfig: ModalConfig = {
    modalTitle: "Create Bootcamp",
    closeButtonLabel: "Save Bootcamp",
    dismissButtonLabel: "Close",
    onClose: this.openModalVerifyCandidate.bind(this)
  }

  constructor(private candidateService: CandidateService, private form: FormBuilder) {
    this.candidateForm = this.form.group([]);
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
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

  async openModalVerifyCandidate() {
    return await this.candidateModalComponent.open({ centered: true, size: 'lg' });
  }
}

