import {Component, OnInit, ChangeDetectorRef} from '@angular/core';
import {Client} from '../../models/client';
import {ClientService} from '../../services/client.service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {DatePipe} from '@angular/common';
import {EditClientModalComponent} from './edit-client-modal/edit-client-modal.component';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [NgbModule, DatePipe, EditClientModalComponent],
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  clients: Client[] = [];

  selectedClient: any = {};

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
        this.cdr.markForCheck();
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

}
