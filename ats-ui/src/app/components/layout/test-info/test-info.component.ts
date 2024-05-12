import {Component, OnInit, ViewChild} from '@angular/core';
import {Config} from "datatables.net";
import {TestService} from "../../../services/TestService";
import {ActivatedRoute} from "@angular/router";
import {TestInfoService} from "../../../services/TestInfoService";
import {TestInfo} from "../../../models/TestInfo";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModalComponent, ModalConfig} from "../modal/modal.component";
import {Candidate} from "../../../models/candidate/Candidate";
import {Test} from "../../../models/Test";
import {CandidateService} from "../../../services/CandidateService";

@Component({
  selector: 'app-test-info',
  templateUrl: './test-info.component.html',
  styleUrls: ['./test-info.component.css']
})
export class TestInfoComponent implements OnInit {

  @ViewChild('testInfoModal') private testInfoModalComponent!: ModalComponent

  isLoading = true;
  testInfos: TestInfo[] = [];
  dtOptionsIT: Config = {};
  dtOptionsICWC: Config = {};

  currentSelectedCandidate: Candidate | undefined;
  currentTestInfoId: number | undefined;
  currentTest: Test | undefined;

  testInfoForm: FormGroup;

  testInfoModalConfig: ModalConfig = {
    modalTitle: "Test Info",
    closeButtonLabel: "Update Test Info",
    dismissButtonLabel: "Close",
    onClose: this.updateTestInfo.bind(this)
  }



  constructor(private candidateService: CandidateService, private testService: TestService, private testInfoService: TestInfoService, private route: ActivatedRoute, private form: FormBuilder) {
    this.testInfoForm = this.form.group({
      technicalNote: ['',Validators.required],
      interviewNote: ['',Validators.required]
    })
  }

  ngOnInit(): void {
    let testId = this.route.snapshot.params['testId'];
    console.log(testId)

    this.dtOptionsIT = {
      pagingType: 'full_numbers',
      columns: [
        {
          name: "ID",
          searchable: false
        },
        {
          name: "Candidate First Name"
        },
        {
          name: "Candidate Last Name"
        },
        {
          name: "Technical Note",
          searchable: false
        },
        {
          name: "Interview Note",
          searchable: false
        },
        {
          name: "Final Note",
          searchable: false
        }
      ]
    };

    this.testService.getTestById(testId).subscribe(test => {
      this.currentTest = test;
    })

    this.testInfoService.getTestInfosByTestId(testId).subscribe((testInfos) => {
      this.testInfos = testInfos;
      console.log(testInfos)
      this.isLoading = false;
    });
  }

  async openTestInfoModal(testId: number, candidate:Candidate) {
    this.currentSelectedCandidate = candidate;
    this.currentTestInfoId = testId;
    return await this.testInfoModalComponent.open({ centered: true, size: 'lg' })
  }

  updateTestInfo() {
    let testInfo: TestInfo = this.testInfoForm.value;
    console.log(testInfo)
    this.testInfoService.updateTestInfo(testInfo, this.currentTestInfoId!).subscribe(data => {
      console.log(data)
    });
    return true;
  }

  refuseCandidate(candidate: Candidate | undefined) {
    this.candidateService.refuseCandidateInTest(candidate?.id!, this.currentTest?.id!).subscribe(candidate => {
      console.log(candidate);
    })
  }

  confirmCandidateList(id: number | undefined) {
    this.testService.changeTestStatusToInTesting(id!).subscribe(test => {
      console.log(test)
    })
  }

  completeTest(id: number | undefined) {
    this.testService.changeTestStatusToTestCompleted(id!).subscribe(test => {
      console.log(test)
    })
  }
}