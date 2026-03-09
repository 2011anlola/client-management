import { Component, Input, OnChanges, SimpleChanges, ViewChild, Output, EventEmitter, AfterViewInit } from '@angular/core';
import { ClientService } from '../../../services/client.service';
import { FormsModule, NgForm } from '@angular/forms';
import { Modal } from 'bootstrap';

/**
 * Modal component for creating a new client.
 */
@Component({
  selector: 'app-create-client-modal',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-client-modal.component.html',
  styleUrls: ['./create-client-modal.component.css']
})
export class CreateClientModalComponent implements OnChanges, AfterViewInit {

  @Input() showCreateModal: boolean = false;
  @Output() clientCreated = new EventEmitter<any>();
  @ViewChild('createForm') createForm?: NgForm;

  newClient = {
    name: '',
    email: '',
    phone: '',
    address: '',
    country: '',
  };

  private modal?: Modal;
  isSubmitting = false;

  constructor(private clientService: ClientService) {}

  /**
   * Lifecycle hook after view initialization.
   */
  ngAfterViewInit(): void {
    console.log('CreateClientModal - ngAfterViewInit, createForm:', this.createForm);
  }

  /**
   * Lifecycle hook for input changes.
   * @param changes the changes object
   */
  ngOnChanges(changes: SimpleChanges): void {
    console.log('CreateClientModal - ngOnChanges triggered:', changes);

    if (changes['showCreateModal']) {
      const shouldShow = changes['showCreateModal'].currentValue;
      console.log('showCreateModal changed to:', shouldShow);

      if (shouldShow) {
        // Always show modal when showCreateModal is true
        setTimeout(() => this.showModal(), 50);
      }
    }
  }

  /**
   * Shows the create modal.
   */
  showModal(): void {
    console.log('showModal called');
    const modalEl = document.getElementById('createClientModal');

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

  /**
   * Closes the create modal.
   */
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
    if (this.createForm) {
      this.createForm.resetForm();
    }
  }

  /**
   * Handles creating a new client.
   */
  createClientHandler(): void {
    console.log('createClientHandler called with:', this.newClient);

    if (this.createForm && this.createForm.invalid) {
      console.log('Form is invalid, marking all fields as touched');
      Object.keys(this.createForm.controls).forEach(key => {
        this.createForm!.controls[key].markAsTouched();
      });
      return;
    }

    if (this.isSubmitting) {
      console.log('Create already in progress, ignoring duplicate submission');
      return;
    }

    this.isSubmitting = true;
    console.log('Calling clientService.createClient');
    this.clientService.createClient(this.newClient)
      .subscribe({
        next: (createdClient) => {
          console.log('Create successful:', createdClient);
          this.isSubmitting = false;
          this.closeModal();
          // Reset form
          this.newClient = {
            name: '',
            email: '',
            phone: '',
            address: '',
            country: '',
          };
          // Emit event so parent can refresh
          this.clientCreated.emit(createdClient);
        },
        error: (error) => {
          console.error('Create failed:', error);
          this.isSubmitting = false;
          alert('Error creating client: ' + (error.error?.message || error.message));
        }
      });
  }

}
