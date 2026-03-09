import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Client } from '../models/client';

/**
 * Service for managing client-related HTTP operations.
 */
@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private apiUrl = 'http://localhost:8080/api/clients';

  constructor(private http: HttpClient) {
  }

  /**
   * Retrieves all clients.
   * @returns Observable of client array
   */
  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.apiUrl);
  }

  /**
   * Creates a new client.
   * @param client the client data
   * @returns Observable of the response
   */
  createClient(client: any): Observable<any> {
    return this.http.post(this.apiUrl, client);
  }

  /**
   * Updates an existing client.
   * @param id the client ID
   * @param client the updated client data
   * @returns Observable of the response
   */
  updateClient(id: number, client: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, client);
  }

  /**
   * Deletes a client by ID.
   * @param id the client ID
   * @returns Observable of the response
   */
  deleteClient(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

}
