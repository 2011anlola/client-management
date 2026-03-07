import {Component, OnInit, ChangeDetectorRef} from '@angular/core';
import {Client} from '../../models/client';
import {ClientService} from '../../services/client.service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {DatePipe} from '@angular/common';
import {EditClientModalComponent} from './edit-client-modal/edit-client-modal.component';
import {CreateClientModalComponent} from './create-client-modal/create-client-modal.component';
import {DeleteClientModalComponent} from './delete-client-modal/delete-client-modal.component';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [NgbModule, DatePipe, EditClientModalComponent, CreateClientModalComponent, DeleteClientModalComponent, FormsModule],
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  clients: Client[] = [];

  selectedClient: any = {};

  selectedClientForDelete: any = null;

  showCreateModal: boolean = false;

  filters = {
    name: '',
    email: '',
    phone: '',
    address: '',
    country: '',
    status: '',
    createdAt: ''
  };

  filteredClients: any[] = [];

  clearFilters() {
    this.filters = {
      name: '',
      email: '',
      phone: '',
      address: '',
      country: '',
      status: '',
      createdAt: ''
    };

    this.filteredClients = [...this.clients];
  }

  constructor(
    private clientService: ClientService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients() {
    //console.log('ClientListComponent initialized');
    this.clientService.getClients().subscribe({
      next: (data) => {
        //console.log('Clients loaded successfully:', data);
        this.clients = data;
        this.filteredClients = [...this.clients];
        this.cdr.markForCheck();
        this.applyFilters();
        //console.log('Clients array set to:', this.clients);
      },
      error: (err) => {
        //console.error('Error fetching clients:', err);
      }
    });
  }

  editClient(client: Client): void {
    console.log('Edit button clicked for client:', client);
    // Reset first to ensure ngOnChanges fires even for same client
    this.selectedClient = null;
    this.cdr.markForCheck();

    // Set the client after a brief delay to trigger change detection
    setTimeout(() => {
      this.selectedClient = client;
      this.cdr.markForCheck();
    }, 10);
  }

  deleteClient(client: Client): void {
    console.log('Delete button clicked for client:', client);
    // Reset first to ensure ngOnChanges fires even for same client
    this.selectedClientForDelete = null;
    this.cdr.markForCheck();

    // Set the client after a brief delay to trigger change detection
    setTimeout(() => {
      this.selectedClientForDelete = client;
      this.cdr.markForCheck();
    }, 10);
  }

  openCreateModal(): void {
    console.log('Create Client button clicked');
    // Reset first to ensure ngOnChanges fires
    this.showCreateModal = false;
    this.cdr.markForCheck();

    // Set to true after a brief delay to trigger change detection
    setTimeout(() => {
      this.showCreateModal = true;
      this.cdr.markForCheck();
    }, 10);
  }

  onClientCreated(client: any) {
    this.clients.unshift(client);
  }

  onClientDeleted(id: number) {
    this.clients = this.clients.filter(c => c.id !== id);
    this.selectedClientForDelete = null;
    this.loadClients();
  }

  applyFilters() {

    this.filteredClients = this.clients.filter(client => {

      const createdDate = client.createdAt
        ? new Date(client.createdAt).toISOString().split('T')[0]
        : '';

      return (
        client.name?.toLowerCase().includes(this.filters.name.toLowerCase()) &&
        client.email?.toLowerCase().includes(this.filters.email.toLowerCase()) &&
        client.phone?.toLowerCase().includes(this.filters.phone.toLowerCase()) &&
        client.address?.toLowerCase().includes(this.filters.address.toLowerCase()) &&
        client.country?.toLowerCase().includes(this.filters.country.toLowerCase()) &&
        (!this.filters.status || client.status?.toUpperCase() === this.filters.status) &&
        (!this.filters.createdAt || createdDate === this.filters.createdAt)
      );

    });

  }
}
