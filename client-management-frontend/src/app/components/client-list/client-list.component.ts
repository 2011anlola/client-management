import {Component, OnInit, ChangeDetectorRef} from '@angular/core';
import {Client} from '../../models/client';
import {ClientService} from '../../services/client.service';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [],
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {

  clients: Client[] = [];

  constructor(
    private clientService: ClientService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    console.log('ClientListComponent initialized');
    this.clientService.getClients().subscribe({
      next: (data) => {
        console.log('Clients loaded successfully:', data);
        this.clients = data;
        this.cdr.markForCheck();
        console.log('Clients array set to:', this.clients);
      },
      error: (err) => {
        console.error('Error fetching clients:', err);
      }
    });
  }

}
