import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { map } from 'rxjs/operators';
import { Client } from '../models/client';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private apiUrl = 'http://localhost:8080/api/clients';

  constructor(private http: HttpClient) {
  }

  getClients(): Observable<Client[]> {
    return this.http.get<any>(this.apiUrl).pipe(
      map(response => {
        console.log('Raw response:', response);
        if (response && Array.isArray(response.content)) {
          return response.content;
        }
        return Array.isArray(response) ? response : [];
      })
    );
  }
}
