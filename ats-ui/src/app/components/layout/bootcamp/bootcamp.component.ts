import {Component, OnInit, ViewChild} from '@angular/core';
import {Bootcamp} from "../../../models/Bootcamp";
import {BootcampService} from "../../../services/BootcampService";
import {Config} from "datatables.net";
import {ModalComponent, ModalConfig} from "../modal/modal.component";

@Component({
  selector: 'app-bootcamp',
  templateUrl: './bootcamp.component.html',
  styleUrls: ['./bootcamp.component.css']
})
export class BootcampComponent implements OnInit {
  @ViewChild('modal') private modalComponent!: ModalComponent

  isLoading = true;
  isCreateBootcampModalOpen = false;
  bootcamps: Bootcamp[] = [];
  dtOptions: Config = {};

  modalConfig: ModalConfig = {
    modalTitle: "Create Bootcamp"
  }

  constructor(bootcampService: BootcampService) {
    bootcampService.getAllBootcamps().subscribe((bootcamps) => {
      this.bootcamps = bootcamps;
      console.log(this.bootcamps);
      this.isLoading = false;
      console.log(this.isLoading)
    });
  }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers'
    };
  }

  async openModal() {
    return await this.modalComponent.open()
  }
}
