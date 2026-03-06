import { Component, EventEmitter, Output } from '@angular/core';
import { ClientService } from '../../../services/client.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-create-client-modal',
  templateUrl: './create-client-modal.component.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./create-client-modal.component.css']
})
export class CreateClientModalComponent {

  @Output() clientCreated = new EventEmitter<any>();

  newClient = {
    name: '',
    email: '',
    phone: '',
    address: '',
    country: '',
  };

  constructor(private clientService: ClientService) {}

  createClient() {
    console.log("Create button clicked");
    this.clientService.createClient(this.newClient).subscribe({
      next: (createdClient) => {

        console.log("Client created:", createdClient);

        this.clientCreated.emit(createdClient);
        this.newClient = {
          name: '',
          email: '',
          phone: '',
          address: '',
          country: '',
        };
      },
      error: (err) => {
        console.error("Create client failed", err);
      }

      // reset form

    });
  }

}
