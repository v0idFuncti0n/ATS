import {Component, OnInit} from '@angular/core';
import {Config} from "datatables.net";
import {TestService} from "../../../services/TestService";
import {Test} from "../../../models/Test";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  isLoading = true;
  tests: Test[] = [];
  dtOptions: Config = {};

  constructor(private testService: TestService, private route: ActivatedRoute, private router: Router) {
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
}
