package com.angellopez.client_management.service;

import com.angellopez.client_management.dto.ClientRequestDTO;
import com.angellopez.client_management.dto.ClientResponseDTO;
import com.angellopez.client_management.entity.Client;
import com.angellopez.client_management.entity.ClientStatus;
import com.angellopez.client_management.exception.ClientNotFoundException;
import com.angellopez.client_management.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing client operations.
 * Provides methods for CRUD operations on clients with filtering support.
 */
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    /**
     * Retrieves a list of clients based on optional filters.
     * @param status optional status filter
     * @param country optional country filter
     * @return list of client response DTOs
     */
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

    /**
     * Retrieves a client by its ID.
     * @param id the client ID
     * @return the client response DTO
     * @throws ClientNotFoundException if the client is not found
     */
    public ClientResponseDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        return mapToResponseDTO(client);
    }

    /**
     * Creates a new client.
     * @param dto the client request DTO
     * @return the created client response DTO
     */
    @Transactional
    public ClientResponseDTO createClient(ClientRequestDTO dto) {
        Client client = mapToEntity(dto);
        client.setStatus(ClientStatus.ACTIVE);
        Client savedClient = clientRepository.save(client);
        return mapToResponseDTO(savedClient);
    }

    /**
     * Updates an existing client.
     * @param id the client ID
     * @param dto the client request DTO with updated data
     * @return the updated client response DTO
     * @throws ClientNotFoundException if the client is not found
     */
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

    /**
     * Deletes a client by its ID.
     * @param id the client ID
     * @throws ClientNotFoundException if the client is not found
     */
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException(id);
        }
        clientRepository.deleteById(id);
    }

    /**
     * Maps a Client entity to a ClientResponseDTO.
     * @param client the client entity
     * @return the client response DTO
     */
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

    /**
     * Maps a ClientRequestDTO to a Client entity.
     * @param dto the client request DTO
     * @return the client entity
     */
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