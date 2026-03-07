import { Component, Input, OnChanges, SimpleChanges, ViewChild, ElementRef, Output, EventEmitter, AfterViewInit } from '@angular/core';
import { ClientService } from '../../../services/client.service';
import {FormsModule, NgForm} from '@angular/forms';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-edit-client-modal',
  standalone: true,
  templateUrl: './edit-client-modal.component.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./edit-client-modal.component.css']
})
export class EditClientModalComponent implements OnChanges, AfterViewInit {

  @Input() client: any = null;
  @Output() clientUpdated = new EventEmitter<void>();
  @ViewChild('editForm') editForm?: NgForm;

  editClient: any = {};
  private modal?: Modal;
  private lastClientId: number | null = null;
  isSubmitting = false;

  constructor(private clientService: ClientService) {}

  ngAfterViewInit(): void {
    console.log('EditClientModal - ngAfterViewInit, editForm:', this.editForm);
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log('EditClientModal - ngOnChanges triggered:', changes);

    if (changes['client']) {
      const currentClient = changes['client'].currentValue;
      console.log('Client changed to:', currentClient);

      // Check if this is a new client or if we're editing the same one again
      if (currentClient && currentClient.id) {
        if (this.lastClientId !== currentClient.id || this.lastClientId === null) {
          // New client selected
          console.log('New client selected, creating copy');
          this.editClient = JSON.parse(JSON.stringify(currentClient));
          this.lastClientId = currentClient.id;
        } else {
          // Same client clicked again - just reopen the modal
          console.log('Same client clicked again, reopening modal');
          this.editClient = JSON.parse(JSON.stringify(currentClient));
        }

        // Always show modal when client is set
        setTimeout(() => this.showModal(), 50);
      }
    }
  }

  showModal(): void {
    console.log('showModal called for client:', this.editClient);
    const modalEl = document.getElementById('editClientModal');

    if (!modalEl) {
      console.error('Modal element not found!');
      return;
    }

    // Dispose old modal instance if it exists
    if (this.modal) {
      console.log('Hiding existing modal instance');
      this.modal.hide();
      // Create a new instance
      this.modal = new Modal(modalEl);
    } else {
      console.log('Creating new Modal instance');
      this.modal = new Modal(modalEl);
    }

    console.log('Showing modal');
    this.modal.show();
  }

  closeModal(): void {
    console.log('closeModal called');
    if (this.modal) {
      this.modal.hide();

      // Clean up backdrop and body classes
      const backdrop = document.querySelector('.modal-backdrop');
      if (backdrop) {
        backdrop.remove();
      }

      // Remove modal-open class from body and restore scrolling
      document.body.classList.remove('modal-open');
      document.body.style.overflow = 'auto';
    }
    // Reset form state when modal closes
    if (this.editForm) {
      this.editForm.resetForm();
    }
  }

  updateClient(): void {
    console.log('updateClient called with:', this.editClient);

    if (this.editForm && this.editForm.invalid) {
      console.log('Form is invalid, marking all fields as touched');
      Object.keys(this.editForm.controls).forEach(key => {
        this.editForm!.controls[key].markAsTouched();
      });
      return;
    }

    if (!this.editClient.id) {
      console.error('No client ID found');
      return;
    }

    if (this.isSubmitting) {
      console.log('Update already in progress, ignoring duplicate submission');
      return;
    }

    this.isSubmitting = true;
    console.log('Calling clientService.updateClient');
    this.clientService.updateClient(this.editClient.id, this.editClient)
      .subscribe({
        next: (response) => {
          console.log('Update successful:', response);
          this.isSubmitting = false;
          this.closeModal();
          // Emit event so parent can refresh
          this.clientUpdated.emit();
        },
        error: (error) => {
          console.error('Update failed:', error);
          this.isSubmitting = false;
          alert('Error updating client: ' + (error.error?.message || error.message));
        }
      });
  }
}
