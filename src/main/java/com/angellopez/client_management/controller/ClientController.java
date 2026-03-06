package com.angellopez.client_management.controller;

import com.angellopez.client_management.dto.ClientRequestDTO;
import com.angellopez.client_management.dto.ClientResponseDTO;
import com.angellopez.client_management.entity.ClientStatus;
import com.angellopez.client_management.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    /* =======================
       GET ALL (with filters)
       ======================= */
    @GetMapping
    public List<ClientResponseDTO> getClients(
            @RequestParam(required = false) ClientStatus status,
            @RequestParam(required = false) String country) {

        return clientService.getClients(status, country);
    }

    /* =======================
       GET BY ID
       ======================= */
    @GetMapping("/{id}")
    public ClientResponseDTO getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    /* =======================
       CREATE
       ======================= */
    @PostMapping
    public ClientResponseDTO createClient(
            @Valid @RequestBody ClientRequestDTO dto) {

        return clientService.createClient(dto);
    }

    /* =======================
       UPDATE
       ======================= */
    @PutMapping("/{id}")
    public ClientResponseDTO updateClient(
            @PathVariable Long id,
            @Valid @RequestBody ClientRequestDTO dto) {

        return clientService.updateClient(id, dto);
    }

    /* =======================
       DELETE
       ======================= */
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}