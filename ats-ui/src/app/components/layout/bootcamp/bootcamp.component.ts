import {Component, OnInit, ViewChild} from '@angular/core';
import {Bootcamp} from "../../../models/Bootcamp";
import {BootcampService} from "../../../services/BootcampService";
import {Config} from "datatables.net";
import {ModalComponent, ModalConfig} from "../modal/modal.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-bootcamp',
  templateUrl: './bootcamp.component.html',
  styleUrls: ['./bootcamp.component.css']
})
export class BootcampComponent implements OnInit {
  @ViewChild('modal') private modalComponent!: ModalComponent

  isLoading = true;
  bootcamps: Bootcamp[] = [];
  dtOptions: Config = {};

  bootcampForm: FormGroup;

  modalConfig: ModalConfig = {
    modalTitle: "Create Bootcamp",
    closeButtonLabel: "Save Bootcamp",
    dismissButtonLabel: "Close",
    onClose: this.saveBootcamp.bind(this)
  }

  constructor(private bootcampService: BootcampService, private form: FormBuilder) {
    this.bootcampForm = this.form.group({
      name: ['',Validators.required],
      startDate: ['',Validators.required],
      endDate: ['',Validators.required],
      candidateNumber: ['',Validators.required],
      skillRequired: ['',Validators.required],
      languageRequired: ['',Validators.required],
      languageLevelRequired: ['',Validators.required],
    });

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

  async openModal() {
    return await this.modalComponent.open({ centered: true, size: 'lg' })
  }

  saveBootcamp(): boolean {
    let bootcampToSave: Bootcamp = this.bootcampForm.value;
    this.bootcampService.createBootcamp(bootcampToSave).subscribe(response => {
      console.log(response)
    });
    return true;
  }
}
