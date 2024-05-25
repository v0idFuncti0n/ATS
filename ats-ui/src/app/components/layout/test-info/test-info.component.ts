import {Component, OnInit, ViewChild} from '@angular/core';
import {Config} from "datatables.net";
import {TestService} from "../../../services/TestService";
import {ActivatedRoute, Router} from "@angular/router";
import {TestInfoService} from "../../../services/TestInfoService";
import {TestInfo} from "../../../models/TestInfo";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModalComponent, ModalConfig} from "../modal/modal.component";
import {Candidate} from "../../../models/candidate/Candidate";
import {Test} from "../../../models/Test";
import {CandidateService} from "../../../services/CandidateService";
import {ToastrService} from "ngx-toastr";

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



  constructor(private candidateService: CandidateService, private testService: TestService, private testInfoService: TestInfoService, private route: ActivatedRoute, private form: FormBuilder, private router: Router, private toastr: ToastrService) {
    this.testInfoForm = this.form.group({
      technicalNote: ['',Validators.required],
      interviewNote: ['',Validators.required]
    })
  }

  ngOnInit(): void {
    let testId = this.route.snapshot.params['testId'];
    console.log(testId)

    this.dtOptionsICWC = {
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
          name: "Phone Number"
        },
        {
          name: "Email"
        },
        {
          name: "Actions",
          searchable: false
        }
      ]
    };

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
        },
        {
          name: "Actions"
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
      this.reloadCurrentRoute();
      this.toastr.success("Note updated successfully!");
    }, error => {
      this.toastr.error("Failed to update note");
    });
    return true;
  }

  refuseCandidate(candidate: Candidate | undefined) {
    this.candidateService.refuseCandidateInTest(candidate?.id!, this.currentTest?.id!).subscribe(candidate => {
      console.log(candidate);
      this.reloadCurrentRoute();
      this.toastr.success("Candidate removed successfully!");
    }, error => {
      this.toastr.error("Failed to remove candidate");
    })
  }

  confirmCandidateList(id: number | undefined) {
    this.testService.changeTestStatusToInTesting(id!).subscribe(test => {
      console.log(test)
      this.reloadCurrentRoute();
      this.toastr.success("Candidate list confirmed successfully!");
    }, error => {
      this.toastr.error("Failed to confirm Candidate list");
    })
  }

  completeTest(id: number | undefined) {
    this.testService.changeTestStatusToTestCompleted(id!).subscribe(test => {
      console.log(test)
      this.reloadCurrentRoute();
      this.toastr.success("Test completed successfully!");
    }, error => {
      this.toastr.error("Failed to complete test");
    })
  }

  reloadCurrentRoute() {
    const currentUrl = this.router.url;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentUrl]);
    });
  }
}
