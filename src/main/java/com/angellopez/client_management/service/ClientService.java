package com.angellopez.client_management.service;

import com.angellopez.client_management.dto.ClientRequestDTO;
import com.angellopez.client_management.dto.ClientResponseDTO;
import com.angellopez.client_management.entity.Client;
import com.angellopez.client_management.entity.ClientStatus;
import com.angellopez.client_management.exception.ClientNotFoundException;
import com.angellopez.client_management.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    /* =======================
       GET (with filtering)
       ======================= */
    public List<ClientResponseDTO> getClients(
            ClientStatus status,
            String country) {

        List<Client> clients;

        if (status != null && country != null) {
            clients = clientRepository.findByStatusAndCountry(status, country);
        } else if (status != null) {
            clients = clientRepository.findByStatus(status);
        } else if (country != null) {
            clients = clientRepository.findByCountry(country);
        } else {
            clients = clientRepository.findAll();
        }

        return clients.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    /* =======================
       GET BY ID
       ======================= */
    public ClientResponseDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        return mapToResponseDTO(client);
    }

    /* =======================
       CREATE
       ======================= */
    @Transactional
    public ClientResponseDTO createClient(ClientRequestDTO dto) {
        Client client = mapToEntity(dto);
        client.setStatus(ClientStatus.ACTIVE);
        Client savedClient = clientRepository.save(client);
        return mapToResponseDTO(savedClient);
    }

    /* =======================
       UPDATE
       ======================= */
    public ClientResponseDTO updateClient(Long id, ClientRequestDTO dto) {

        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        existingClient.setName(dto.getName());
        existingClient.setEmail(dto.getEmail());
        existingClient.setPhone(dto.getPhone());
        existingClient.setAddress(dto.getAddress());
        existingClient.setCountry(dto.getCountry());
        existingClient.setStatus(dto.getStatus());

        Client updatedClient = clientRepository.save(existingClient);
        return mapToResponseDTO(updatedClient);
    }

    /* =======================
       DELETE
       ======================= */
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException(id);
        }
        clientRepository.deleteById(id);
    }

    /* =======================
       MAPPERS
       ======================= */
    private ClientResponseDTO mapToResponseDTO(Client client) {
        return ClientResponseDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .address(client.getAddress())
                .country(client.getCountry())
                .status(client.getStatus())
                .createdAt(client.getCreatedAt())
                .updatedAt(client.getUpdatedAt())
                .build();
    }

    private Client mapToEntity(ClientRequestDTO dto) {
        return Client.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .country(dto.getCountry())
                .status(dto.getStatus())
                .build();
    }
}