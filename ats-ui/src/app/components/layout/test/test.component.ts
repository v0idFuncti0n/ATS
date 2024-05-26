import {Component, OnInit, ViewChild} from '@angular/core';
import {Config} from "datatables.net";
import {TestService} from "../../../services/TestService";
import {Test} from "../../../models/Test";
import {ActivatedRoute, Router} from "@angular/router";
import {ModalComponent, ModalConfig} from "../modal/modal.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  @ViewChild('testUpdateModal') private testUpdateModalComponent!: ModalComponent

  isLoading = true;
  tests: Test[] = [];
  dtOptions: Config = {};

  testUpdateForm: FormGroup;
  currentTest: Test | undefined;

  testUpdateModalConfig: ModalConfig = {
    modalTitle: "Update test",
    closeButtonLabel: "Update Test",
    dismissButtonLabel: "Close",
    onClose: this.updateTest.bind(this)
  }

  constructor(private testService: TestService, private route: ActivatedRoute, private router: Router, private form: FormBuilder) {
    this.testUpdateForm = this.form.group({
      startDate: ['' ,Validators.required],
      endDate: ['' ,Validators.required],
      candidateNumber: ['' ,Validators.required]
    })
  }

  ngOnInit(): void {
    let testId = this.route.snapshot.params['testId'];

    this.dtOptions = {
      pagingType: 'full_numbers',
      searchCols: [
        {
          search: testId ? testId : ""
        }
      ],
      columns: [
        {
          name: "ID"
        },
        {
          name: "Start Date",
          searchable: false
        },
        {
          name: "End Date",
          searchable: false
        },
        {
          name: "Candidate Number",
          searchable: false
        },
        {
          name: "Status"
        },
        {
          name: "Action",
          searchable: false
        }
      ]
    };

    this.testService.getAllTests().subscribe((tests) => {
      this.tests = tests;
      console.log(tests)
      this.isLoading = false;
    });
  }

  goToTestInfo(testId: number | undefined) {
    this.router.navigate(['dashboard/testInfo', testId]);
  }

  reloadCurrentRoute() {
    this.isLoading = true;
    this.testService.getAllTests().subscribe((tests) => {
      this.tests = tests;
      console.log(tests)
      this.isLoading = false;
    });
  }

  updateTest() {
    this.testService.updateTest(this.testUpdateForm.value, this.currentTest!.id!).subscribe(() => {
      this.reloadCurrentRoute();
    }, (err) => {
      console.log(err)
    })
    return true;
  }

  deleteTest(id: number) {
    this.testService.deleteTest(id).subscribe(() => {
      this.reloadCurrentRoute();
    }, () => {
      this.reloadCurrentRoute();
    })
  }

  async openUpdateTestModal(test: Test) {
    this.currentTest = test;
    this.testUpdateForm = this.form.group({
      startDate: [test.startDate ,Validators.required],
      endDate: [test.endDate ,Validators.required],
      candidateNumber: [test.candidateNumber ,Validators.required]
    })
    return await this.testUpdateModalComponent.open({ centered: true, size: 'lg' })
  }
}
