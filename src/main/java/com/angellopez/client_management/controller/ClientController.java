package com.angellopez.client_management.controller;

import com.angellopez.client_management.dto.ClientRequestDTO;
import com.angellopez.client_management.dto.ClientResponseDTO;
import com.angellopez.client_management.entity.ClientStatus;
import com.angellopez.client_management.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing client resources.
 * Provides endpoints for CRUD operations on clients.
 */
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    /**
     * GET /api/clients - Retrieves a list of clients, optionally filtered by status and country.
     * @param status optional query parameter for client status
     * @param country optional query parameter for country
     * @return list of client response DTOs
     */
    @GetMapping
    public List<ClientResponseDTO> getClients(
            @RequestParam(required = false) ClientStatus status,
            @RequestParam(required = false) String country) {

        return clientService.getClients(status, country);
    }

    /**
     * GET /api/clients/{id} - Retrieves a client by its ID.
     * @param id the client ID
     * @return the client response DTO
     */
    @GetMapping("/{id}")
    public ClientResponseDTO getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    /**
     * POST /api/clients - Creates a new client.
     * @param dto the client request DTO in the request body
     * @return the created client response DTO
     */
    @PostMapping
    public ClientResponseDTO createClient(
            @Valid @RequestBody ClientRequestDTO dto) {

        return clientService.createClient(dto);
    }

    /**
     * PUT /api/clients/{id} - Updates an existing client.
     * @param id the client ID
     * @param dto the client request DTO in the request body
     * @return the updated client response DTO
     */
    @PutMapping("/{id}")
    public ClientResponseDTO updateClient(
            @PathVariable Long id,
            @Valid @RequestBody ClientRequestDTO dto) {

        return clientService.updateClient(id, dto);
    }

    /**
     * DELETE /api/clients/{id} - Deletes a client by its ID.
     * @param id the client ID
     */
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}