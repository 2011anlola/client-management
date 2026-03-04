package com.angellopez.client_management.service;

import com.angellopez.client_management.entity.Client;
import com.angellopez.client_management.repository.ClientRepository;
import com.angellopez.client_management.exception.ClientNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // List all clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Get client by ID
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id " + id));
    }

    //Create new client
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Update existing client
    public Client updateClient(Long id, Client updatedClient) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        existingClient.setName(updatedClient.getName());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPhone(updatedClient.getPhone());
        existingClient.setAddress(updatedClient.getAddress());
        existingClient.setCountry(updatedClient.getCountry());
        existingClient.setStatus(updatedClient.getStatus());

        return clientRepository.save(existingClient);
    }

    // Delete client
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
