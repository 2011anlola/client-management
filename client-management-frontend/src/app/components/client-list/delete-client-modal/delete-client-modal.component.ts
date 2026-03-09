import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, AfterViewInit } from '@angular/core';
import { ClientService } from '../../../services/client.service';
import { CommonModule } from '@angular/common';
import { Modal } from 'bootstrap';

/**
 * Modal component for deleting a client.
 */
@Component({
  selector: 'app-delete-client-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './delete-client-modal.component.html',
  styleUrl: './delete-client-modal.component.css'
})
export class DeleteClientModalComponent implements OnChanges, AfterViewInit {

  @Input() client: any = null;

  @Output() clientDeleted = new EventEmitter<number>();

  private modal?: Modal;
  private lastClientId: number | null = null;
  isDeleting = false;

  constructor(private clientService: ClientService) {}

  /**
   * Lifecycle hook after view initialization.
   */
  ngAfterViewInit(): void {
    console.log('DeleteClientModal - ngAfterViewInit');
  }

  /**
   * Lifecycle hook for input changes.
   * @param changes the changes object
   */
  ngOnChanges(changes: SimpleChanges): void {
    console.log('DeleteClientModal - ngOnChanges triggered:', changes);

    if (changes['client']) {
      const currentClient = changes['client'].currentValue;
      console.log('Client changed to:', currentClient);

      // Check if this is a new client or if we're deleting the same one again
      if (currentClient && currentClient.id) {
        if (this.lastClientId !== currentClient.id || this.lastClientId === null) {
          // New client selected
          console.log('New client selected');
          this.lastClientId = currentClient.id;
        } else {
          // Same client clicked again - just reopen the modal
          console.log('Same client clicked again, reopening modal');
        }

        // Always show modal when client is set
        setTimeout(() => this.showModal(), 50);
      }
    }

  }

  /**
   * Shows the delete modal.
   */
  showModal(): void {
    console.log('showModal called for client:', this.client);
    const modalEl = document.getElementById('deleteClientModal');

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
   * Closes the delete modal.
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
  }

  /**
   * Deletes the client.
   */
  deleteClient(): void {
    console.log('deleteClient called for client:', this.client);

    if (!this.client || !this.client.id) {
      console.error('No client ID found');
      return;
    }

    if (this.isDeleting) {
      console.log('Delete already in progress, ignoring duplicate submission');
      return;
    }

    this.isDeleting = true;
    console.log('Calling clientService.deleteClient');
    this.clientService.deleteClient(this.client.id)
      .subscribe({
        next: () => {
          console.log('Delete successful');
          this.isDeleting = false;
          this.closeModal();
          // Emit event so parent can refresh
          this.clientDeleted.emit(this.client.id);
        },
        error: (error) => {
          console.error('Delete failed:', error);
          this.isDeleting = false;
          alert('Error deleting client: ' + (error.error?.message || error.message));
        }
      });
  }

}
