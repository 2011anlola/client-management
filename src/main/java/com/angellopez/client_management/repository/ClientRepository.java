package com.angellopez.client_management.repository;

import com.angellopez.client_management.entity.Client;
import com.angellopez.client_management.entity.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Client entities.
 * Provides CRUD operations and custom queries for clients.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Finds clients by their status.
     * @param status the client status
     * @return list of clients with the given status
     */
    List<Client> findByStatus(ClientStatus status);

    /**
     * Finds clients by their country.
     * @param country the country
     * @return list of clients from the given country
     */
    List<Client> findByCountry(String country);

    /**
     * Finds clients by status and country.
     * @param status the client status
     * @param country the country
     * @return list of clients matching both criteria
     */
    List<Client> findByStatusAndCountry(ClientStatus status, String country);
}