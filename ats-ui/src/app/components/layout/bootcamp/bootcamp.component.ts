import {Component, OnInit, ViewChild} from '@angular/core';
import {Bootcamp} from "../../../models/Bootcamp";
import {BootcampService} from "../../../services/BootcampService";
import {Config} from "datatables.net";
import {ModalComponent, ModalConfig} from "../modal/modal.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TestService} from "../../../services/TestService";
import {Test} from "../../../models/Test";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-bootcamp',
  templateUrl: './bootcamp.component.html',
  styleUrls: ['./bootcamp.component.css']
})
export class BootcampComponent implements OnInit {
  @ViewChild('bootcampModal') private bootcampModalComponent!: ModalComponent
  @ViewChild('bootcampUpdateModal') private bootcampUpdateModalComponent!: ModalComponent
  @ViewChild('testModal') private testModalComponent!: ModalComponent

  isLoading = true;
  currentSelectedBootcampId: number | undefined = -1;

  bootcamps: Bootcamp[] = [];
  dtOptions: Config = {};

  bootcampForm: FormGroup;
  bootcampUpdateForm: FormGroup;
  testForm: FormGroup;

  bootcampModalConfig: ModalConfig = {
    modalTitle: "Create Bootcamp",
    closeButtonLabel: "Save Bootcamp",
    dismissButtonLabel: "Close",
    onClose: this.saveBootcamp.bind(this)
  }

  bootcampUpdateModalConfig: ModalConfig = {
    modalTitle: "Update Bootcamp",
    closeButtonLabel: "Update Bootcamp",
    dismissButtonLabel: "Close",
    onClose: this.updateBootcamp.bind(this)
  }

  testModalConfig: ModalConfig = {
    modalTitle: "Create Test",
    closeButtonLabel: "Save Test",
    dismissButtonLabel: "Close",
    onClose: this.saveTest.bind(this)
  }

  constructor(private bootcampService: BootcampService, private testService: TestService, private form: FormBuilder, private router: Router, private toastr: ToastrService) {
    this.bootcampForm = this.form.group({
      name: ['',Validators.required],
      startDate: ['',Validators.required],
      endDate: ['',Validators.required],
      candidateNumber: ['',Validators.required],
      skillRequired: ['',Validators.required],
      languageRequired: ['',Validators.required],
      languageLevelRequired: ['',Validators.required],
    });

    this.bootcampUpdateForm = this.form.group({
      name: ['',Validators.required],
      startDate: ['',Validators.required],
      endDate: ['',Validators.required],
      candidateNumber: ['',Validators.required],
      skillRequired: ['',Validators.required],
      languageRequired: ['',Validators.required],
      languageLevelRequired: ['',Validators.required],
    });

    this.testForm = this.form.group({
      startDate: ['',Validators.required],
      endDate: ['',Validators.required],
      candidateNumber: ['',Validators.required]
    })

    bootcampService.getAllBootcamps().subscribe((bootcamps) => {
      this.bootcamps = bootcamps;
      this.isLoading = false;
    });
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      order: [[0, 'desc']]
    };
  }

  async openBootcampModal() {
    return await this.bootcampModalComponent.open({ centered: true, size: 'lg' })
  }

  saveBootcamp(): boolean {
    let bootcampToSave: Bootcamp = this.bootcampForm.value;
    this.bootcampService.createBootcamp(bootcampToSave).subscribe(response => {
      this.reloadCurrentRoute();
      this.toastr.success("Bootcamp created successfully!");
    }, error => {
      this.toastr.error("Failed to create bootcamp");

    });
    return true;
  }

  async openBootcampUpdateModal(bootcamp: Bootcamp | undefined) {
    this.currentSelectedBootcampId = bootcamp!.id;
    this.bootcampUpdateForm = this.form.group({
      name: [bootcamp?.name,Validators.required],
      startDate: [bootcamp?.startDate,Validators.required],
      endDate: [bootcamp?.endDate,Validators.required],
      candidateNumber: [bootcamp?.candidateNumber,Validators.required],
      skillRequired: [bootcamp?.skillRequired,Validators.required],
      languageRequired: [bootcamp?.languageRequired,Validators.required],
      languageLevelRequired: [bootcamp?.languageLevelRequired,Validators.required]
    });
    return await this.bootcampUpdateModalComponent.open({ centered: true, size: 'lg' })
  }

  updateBootcamp(): boolean {
    let bootcampToUpdate: Bootcamp = this.bootcampUpdateForm!.value;
    this.bootcampService.updateBootcamp(bootcampToUpdate, this.currentSelectedBootcampId!).subscribe(response => {
      this.reloadCurrentRoute();
    });
    return true;
  }

  async openTestModal(bootcampId: number | undefined) {
    this.currentSelectedBootcampId = bootcampId;
    return await this.testModalComponent.open({ centered: true, size: 'lg' })
  }

  saveTest(): boolean {
    let testToSave: Test = this.testForm.value;
    this.testService.createTest(testToSave, this.currentSelectedBootcampId!).subscribe(response => {
      this.reloadCurrentRoute();
      this.toastr.success("Test created successfully!");
    }, error => {
      this.toastr.error("Failed to create test");
    });
    return true;
  }

  goToTest(testId: number | undefined) {
    this.router.navigate(['/dashboard/test',{testId: testId}]);
  }

  reloadCurrentRoute() {
    this.isLoading = true;
    this.bootcampService.getAllBootcamps().subscribe((bootcamps) => {
      this.bootcamps = bootcamps;
      this.isLoading = false;
    });
  }

  deleteBootcamp(id: number) {
    this.bootcampService.deleteBootcamp(id).subscribe(() => {
      this.reloadCurrentRoute()
    }, ()=> {
    })
  }
}
