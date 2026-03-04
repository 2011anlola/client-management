package com.angellopez.client_management.service;

import com.angellopez.client_management.dto.ClientRequestDTO;
import com.angellopez.client_management.dto.ClientResponseDTO;
import com.angellopez.client_management.entity.Client;
import com.angellopez.client_management.entity.ClientStatus;
import com.angellopez.client_management.exception.ClientNotFoundException;
import com.angellopez.client_management.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    /* =======================
       CREATE CLIENT TEST
       ======================= */
    @Test
    void shouldCreateClientSuccessfully() {

        ClientRequestDTO dto = new ClientRequestDTO();
        dto.setName("John Doe");
        dto.setEmail("john@email.com");
        dto.setCountry("USA");
        dto.setStatus(ClientStatus.ACTIVE);

        Client savedClient = Client.builder()
                .id(1L)
                .name("John Doe")
                .email("john@email.com")
                .country("USA")
                .status(ClientStatus.ACTIVE)
                .build();

        when(clientRepository.save(any(Client.class)))
                .thenReturn(savedClient);

        var response = clientService.createClient(dto);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        assertEquals(1L, response.getId());

        verify(clientRepository, times(1)).save(any(Client.class));
    }

    /* =======================
       GET CLIENT BY ID SUCCESS
       ======================= */
    @Test
    void shouldReturnClientById() {

        Client client = Client.builder()
                .id(1L)
                .name("John Doe")
                .email("john@email.com")
                .country("USA")
                .status(ClientStatus.ACTIVE)
                .build();

        when(clientRepository.findById(1L))
                .thenReturn(Optional.of(client));

        var response = clientService.getClientById(1L);

        assertEquals("John Doe", response.getName());
        verify(clientRepository).findById(1L);
    }

    /* =======================
       GET CLIENT BY ID NOT FOUND
       ======================= */
    @Test
    void shouldThrowExceptionWhenClientNotFound() {

        when(clientRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> {
            clientService.getClientById(99L);
        });

        verify(clientRepository).findById(99L);
    }

    @Test
    void shouldUpdateClientSuccessfully() {
        ClientRequestDTO dto = new ClientRequestDTO();
        dto.setName("Jane Doe");
        dto.setEmail("jane@email.com");
        dto.setCountry("Canada");
        dto.setStatus(ClientStatus.INACTIVE);

        Client existingClient = Client.builder()
                .id(1L)
                .name("John Doe")
                .email("john@email.com")
                .country("USA")
                .status(ClientStatus.ACTIVE)
                .build();

        Client updatedClient = Client.builder()
                .id(1L)
                .name("Jane Doe")
                .email("jane@email.com")
                .country("Canada")
                .status(ClientStatus.INACTIVE)
                .build();

        when(clientRepository.findById(1L)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(updatedClient);

        var response = clientService.updateClient(1L, dto);

        assertEquals("Jane Doe", response.getName());
        assertEquals(ClientStatus.INACTIVE, response.getStatus());

        verify(clientRepository).findById(1L);
        verify(clientRepository).save(existingClient);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentClient() {
        ClientRequestDTO dto = new ClientRequestDTO();
        dto.setName("Jane Doe");

        when(clientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> {
            clientService.updateClient(99L, dto);
        });

        verify(clientRepository).findById(99L);
        verify(clientRepository, never()).save(any());
    }

    @Test
    void shouldDeleteClientSuccessfully() {
        when(clientRepository.existsById(1L)).thenReturn(true);

        clientService.deleteClient(1L);

        verify(clientRepository).existsById(1L);
        verify(clientRepository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentClient() {
        when(clientRepository.existsById(99L)).thenReturn(false);

        assertThrows(ClientNotFoundException.class, () -> {
            clientService.deleteClient(99L);
        });

        verify(clientRepository).existsById(99L);
        verify(clientRepository, never()).deleteById(any());
    }

    private Client createClient(Long id, String name, String country, ClientStatus status) {
        return Client.builder()
                .id(id)
                .name(name)
                .email(name.toLowerCase() + "@email.com")
                .country(country)
                .status(status)
                .build();
    }

    @Test
    void shouldReturnPaginatedClientsWithoutFilters() {

        Pageable pageable = PageRequest.of(0, 2);

        var clients = List.of(
                createClient(1L, "Alicia", "Spain", ClientStatus.ACTIVE),
                createClient(2L, "Zach", "Australia", ClientStatus.INACTIVE)
        );

        Page<Client> page = new PageImpl<>(clients, pageable, clients.size());

        when(clientRepository.findAll(pageable)).thenReturn(page);

        Page<ClientResponseDTO> response = clientService.getClients(null, null, pageable);

        assertEquals(2, response.getContent().size());
        assertEquals("Alicia", response.getContent().get(0).getName());
        assertEquals("Zach", response.getContent().get(1).getName());
        verify(clientRepository).findAll(pageable);
    }

    @Test
    void shouldReturnClientsFilteredByStatus() {

        Pageable pageable = PageRequest.of(0, 2);

        var clients = List.of(
                createClient(1L, "Alicia", "Spain", ClientStatus.ACTIVE)
        );

        Page<Client> page = new PageImpl<>(clients, pageable, clients.size());

        when(clientRepository.findByStatus(ClientStatus.ACTIVE, pageable)).thenReturn(page);

        Page<ClientResponseDTO> response = clientService.getClients(ClientStatus.ACTIVE, null, pageable);

        assertEquals(1, response.getContent().size());
        assertEquals(ClientStatus.ACTIVE, response.getContent().get(0).getStatus());

        verify(clientRepository).findByStatus(ClientStatus.ACTIVE, pageable);
    }

    @Test
    void shouldReturnClientsFilteredByCountry() {

        Pageable pageable = PageRequest.of(0, 2);

        var clients = List.of(
                createClient(1L, "Bob", "Canada", ClientStatus.INACTIVE)
        );

        Page<Client> page = new PageImpl<>(clients, pageable, clients.size());

        when(clientRepository.findByCountry("Canada", pageable)).thenReturn(page);

        Page<ClientResponseDTO> response = clientService.getClients(null, "Canada", pageable);

        assertEquals(1, response.getContent().size());
        assertEquals("Canada", response.getContent().get(0).getCountry());

        verify(clientRepository).findByCountry("Canada", pageable);
    }

    @Test
    void shouldReturnClientsFilteredByStatusAndCountry() {

        Pageable pageable = PageRequest.of(0, 2);

        var clients = List.of(
                createClient(1L, "Alice", "USA", ClientStatus.ACTIVE)
        );

        Page<Client> page = new PageImpl<>(clients, pageable, clients.size());

        when(clientRepository.findByStatusAndCountry(ClientStatus.ACTIVE, "USA", pageable))
                .thenReturn(page);

        Page<ClientResponseDTO> response = clientService.getClients(ClientStatus.ACTIVE, "USA", pageable);

        assertEquals(1, response.getContent().size());
        assertEquals("Alice", response.getContent().get(0).getName());
        assertEquals(ClientStatus.ACTIVE, response.getContent().get(0).getStatus());
        assertEquals("USA", response.getContent().get(0).getCountry());

        verify(clientRepository).findByStatusAndCountry(ClientStatus.ACTIVE, "USA", pageable);
    }
}