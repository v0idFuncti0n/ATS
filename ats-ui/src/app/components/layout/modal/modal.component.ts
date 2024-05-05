import {Component, Input, TemplateRef, ViewChild} from '@angular/core';
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {NgbModalOptions} from "@ng-bootstrap/ng-bootstrap/modal/modal-config";

export interface ModalConfig {
  modalTitle: string
  dismissButtonLabel?: string
  closeButtonLabel?: string
  shouldClose?(): Promise<boolean> | boolean
  shouldDismiss?(): Promise<boolean> | boolean
  onClose?(): Promise<boolean> | boolean
  onDismiss?(): Promise<boolean> | boolean
  disableCloseButton?(): boolean
  disableDismissButton?(): boolean
  hideCloseButton?(): boolean
  hideDismissButton?(): boolean
}

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent {
  @Input() public modalConfig!: ModalConfig
  @ViewChild('modal') private modalContent!: TemplateRef<ModalComponent>
  private modalRef!: NgbModalRef

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void { }

  open(modalConfig: NgbModalOptions) {
    return new Promise<boolean>(resolve => {
      this.modalRef = this.modalService.open(this.modalContent, modalConfig)
      this.modalRef.result.then(resolve, resolve)
    })
  }

  async close(): Promise<void> {
    if (this.modalConfig.shouldClose === undefined || (await this.modalConfig.shouldClose())) {
      const result = this.modalConfig.onClose === undefined || (await this.modalConfig.onClose())
      this.modalRef.close(result)
    }
  }

  async dismiss(): Promise<void> {
    if (this.modalConfig.shouldDismiss === undefined || (await this.modalConfig.shouldDismiss())) {
      const result = this.modalConfig.onDismiss === undefined || (await this.modalConfig.onDismiss())
      this.modalRef.dismiss(result)
    }
  }
}
