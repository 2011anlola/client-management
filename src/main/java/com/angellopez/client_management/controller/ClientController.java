package com.angellopez.client_management.controller;

import com.angellopez.client_management.entity.Client;
import com.angellopez.client_management.entity.ClientStatus;
import com.angellopez.client_management.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // GET all clients
    @GetMapping
    public Page<Client> getClients(
            @RequestParam(required = false) ClientStatus status,
            @RequestParam(required = false) String country,
            @PageableDefault(size = 5) Pageable pageable) {

        return clientService.getClients(status, country, pageable);
    }

    // GET client by ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    // POST create client
    @PostMapping
    public Client createClient(@Valid @RequestBody Client client) {
        return clientService.createClient(client);
    }

    // PUT update client
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @Valid @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    // DELETE client
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
