package com.angellopez.client_management.service;

import com.angellopez.client_management.dto.ClientRequestDTO;
import com.angellopez.client_management.entity.Client;
import com.angellopez.client_management.entity.ClientStatus;
import com.angellopez.client_management.exception.ClientNotFoundException;
import com.angellopez.client_management.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ClientService class.
 * Tests CRUD operations and exception handling.
 */
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    /**
     * Tests successful client creation.
     */
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

    /**
     * Tests retrieving a client by ID successfully.
     */
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

    /**
     * Tests that ClientNotFoundException is thrown when retrieving a non-existent client.
     */
    @Test
    void shouldThrowExceptionWhenClientNotFound() {

        when(clientRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> {
            clientService.getClientById(99L);
        });

        verify(clientRepository).findById(99L);
    }

    /**
     * Tests successful client update.
     */
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

    /**
     * Tests that ClientNotFoundException is thrown when updating a non-existent client.
     */
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

    /**
     * Tests successful client deletion.
     */
    @Test
    void shouldDeleteClientSuccessfully() {
        when(clientRepository.existsById(1L)).thenReturn(true);

        clientService.deleteClient(1L);

        verify(clientRepository).existsById(1L);
        verify(clientRepository).deleteById(1L);
    }

    /**
     * Tests that ClientNotFoundException is thrown when deleting a non-existent client.
     */
    @Test
    void shouldThrowExceptionWhenDeletingNonExistentClient() {
        when(clientRepository.existsById(99L)).thenReturn(false);

        assertThrows(ClientNotFoundException.class, () -> {
            clientService.deleteClient(99L);
        });

        verify(clientRepository).existsById(99L);
        verify(clientRepository, never()).deleteById(any());
    }

}