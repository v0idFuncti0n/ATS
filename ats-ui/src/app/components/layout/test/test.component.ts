import {Component, OnInit} from '@angular/core';
import {Config} from "datatables.net";
import {TestService} from "../../../services/TestService";
import {Test} from "../../../models/Test";

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {

  isLoading = true;
  tests: Test[] = [];
  dtOptions: Config = {};

  constructor(private testService: TestService) {
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
    };

    this.testService.getAllTests().subscribe((tests) => {
      this.tests = tests;
      console.log(tests)
      this.isLoading = false;
    });
  }
}
